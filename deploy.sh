#!/bin/bash
set -e

# === CONFIGURACIÓN ===
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

# === FUNCIÓN SPINNER ===
spinner() {
  local pid=$1
  local msg=$2
  local spin='⠋⠙⠹⠸⠼⠴⠦⠧⠇⠏'
  local i=0
  tput civis
  while kill -0 $pid 2>/dev/null; do
    i=$(( (i+1) % 10 ))
    printf "\r%s %s" "${spin:$i:1}" "$msg"
    sleep 0.1
  done
  printf "\r✅ %s\n" "$msg"
  tput cnorm
}

echo "🚀 Iniciando build y despliegue..."

# === ARCHIVO DE CONFIGURACIÓN ===
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

# === LOGS ===
if [ ! -d "$LOGS_DIR" ]; then
  echo "🪵 Creando carpeta de logs en $LOGS_DIR..."
  mkdir -p "$LOGS_DIR"
else
  echo "✅ Carpeta de logs existente: $LOGS_DIR"
fi

# === COMPILACIÓN ===
build_module() {
  local path="$1"
  local name="$2"
  cd "$path"
  mvn clean compile package dependency:copy-dependencies >"$LOGS_DIR/build_${name}.log" 2>&1 &
  pid=$!
  spinner $pid "Compilando módulo: $name..."
}

build_module "$TPGR40_DIR" "tpgr40"
build_module "$BASE_DIR/webdinamica" "webdinamica"
build_module "$BASE_DIR/webmovil" "webmovil"

# === DEPLOY A TOMCAT ===
echo "📂 Copiando archivos .war a Tomcat..."
cp "$BASE_DIR/webdinamica/target/webdinamica-0.0.1-SNAPSHOT.war" "$TOMCAT_DIR/webapps/webdinamica.war"
cp "$BASE_DIR/webmovil/target/webmovil-0.0.1-SNAPSHOT.war" "$TOMCAT_DIR/webapps/webmovil.war"

# === REINICIO DE TOMCAT ===
echo "🛑 Apagando Tomcat..."
"$TOMCAT_DIR/bin/shutdown.sh" > /dev/null 2>&1 || true
sleep 3
echo "▶️ Iniciando Tomcat..."
"$TOMCAT_DIR/bin/startup.sh" > /dev/null 2>&1 &
spinner $! "Levantando servidor Tomcat..."

# === MOSTRAR URLs ===
IP=$(grep "^IP=" "$CONFIG_FILE" | cut -d'=' -f2)
if [ -z "$IP" ]; then
  IP="127.0.0.1"
fi

echo ""
echo "🌐 Aplicaciones disponibles en:"
echo "   👉 http://$IP:8080/webdinamica"
echo "   👉 http://$IP:8080/webmovil"
echo ""

# === SERVIDOR CENTRAL ===
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
