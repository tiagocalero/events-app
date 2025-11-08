#!/bin/bash
set -e  # detiene el script si ocurre un error

# Rutas base
BASE_DIR="$(pwd)"
TOMCAT_DIR="$BASE_DIR/apache-tomcat-11.0.13"
CONFIG_DIR="$HOME/.eventosUy"
CONFIG_FILE="$CONFIG_DIR/configuracion.properties"
LOGS_DIR="$TOMCAT_DIR/logs"
TPGR40_DIR="$BASE_DIR/tpgr40"

RUN_SERVER=false
if [ "$1" == "--run" ]; then
    RUN_SERVER=true
fi

echo "🚀 Iniciando build y despliegue..."

# 0️⃣ Verificar/crear archivo de configuración
if [ ! -f "$CONFIG_FILE" ]; then
    echo "⚙️  Creando archivo de configuración en $CONFIG_FILE..."
    mkdir -p "$CONFIG_DIR"
    cat > "$CONFIG_FILE" <<EOF
IP=127.0.0.1
PUERTO_EVENTO=9128
PUERTO_USUARIO=9129
PUERTO_INSTITUCION=9130
PUERTO_CATEGORIA=9131
EOF
else
    echo "✅ Archivo de configuración existente: $CONFIG_FILE"
fi

# 0️⃣.5 Crear carpeta de logs si no existe
if [ ! -d "$LOGS_DIR" ]; then
    echo "🪵 Creando carpeta de logs en $LOGS_DIR..."
    mkdir -p "$LOGS_DIR"
else
    echo "✅ Carpeta de logs existente: $LOGS_DIR"
fi

# 1️⃣ tpgr40
echo "📦 Compilando tpgr40..."
cd "$TPGR40_DIR"
mvn package dependency:copy-dependencies

# 2️⃣ webdinamica
echo "📦 Compilando webdinamica..."
cd "$BASE_DIR/webdinamica"
mvn package

# 3️⃣ webmovil
echo "📦 Compilando webmovil..."
cd "$BASE_DIR/webmovil"
mvn package

# 4️⃣ Copiar los .war a Tomcat
echo "📂 Copiando archivos .war a Tomcat..."
cp "$BASE_DIR/webdinamica/target/webdinamica-0.0.1-SNAPSHOT.war" "$TOMCAT_DIR/webapps/webdinamica.war"
cp "$BASE_DIR/webmovil/target/webmovil-0.0.1-SNAPSHOT.war" "$TOMCAT_DIR/webapps/webmovil.war"

# 5️⃣ Reiniciar Tomcat
echo "🛑 Apagando Tomcat..."
"$TOMCAT_DIR/bin/shutdown.sh" || true

sleep 3

echo "▶️ Iniciando Tomcat..."
"$TOMCAT_DIR/bin/startup.sh"

# 6️⃣ Mostrar URLs de despliegue
IP=$(grep "^IP=" "$CONFIG_FILE" | cut -d'=' -f2)
if [ -z "$IP" ]; then
    IP="127.0.0.1"
fi

echo ""
echo "🌐 Aplicaciones disponibles en:"
echo "   👉 http://$IP:8080/webdinamica"
echo "   👉 http://$IP:8080/webmovil"
echo ""

# 7️⃣ Mensaje o ejecución del servidor central
if [ "$RUN_SERVER" = true ]; then
    echo "🖥️  Iniciando servidor central..."
    cd "$TPGR40_DIR"
    java -cp "target/tpgr40-0.0.1-SNAPSHOT.jar:target/dependency/*" main.java.publicar.Main
else
    echo "🖥️  Para iniciar el servidor central manualmente, ejecutá:"
    echo ""
    echo "   cd \"$TPGR40_DIR\""
    echo "   java -cp \"target/tpgr40-0.0.1-SNAPSHOT.jar:target/dependency/*\" main.java.publicar.Main"
    echo ""
    echo "✅ Despliegue completado exitosamente."
fi
