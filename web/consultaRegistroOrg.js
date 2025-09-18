document.addEventListener('DOMContentLoaded', () => {

  const datosEventos = {
    "Maraton de Montevideo 2024": {
      usuarios: {
        "atorres": {
          fechaRegistro: "30/07/2024",
          tipoRegistro: "TR07",
          codigoPatrocinio: "Sí"
        }
      }
    },
    
    "Web Summit 2026": {
      usuarios: {
        "atorres": {
          fechaRegistro: "21/08/2025",
          tipoRegistro: "TR25",
          codigoPatrocinio: "Sí"
        }
      }
    }
  };

  const edicionSelect = document.getElementById('edicionSelect');
  const usuarioSelect = document.getElementById('usuarioSelect');
  const usuarioHeader = document.getElementById('usuarioHeader');
  const fechaRegistroInput = document.getElementById('fecha-registro');
  const tipoRegistroInput = document.getElementById('tipo-registro');
  const codigoPatrocinioInput = document.getElementById('codigo-patrocinio');

  edicionSelect.addEventListener('change', () => {
    const edicionSeleccionada = edicionSelect.value;
    
    usuarioSelect.innerHTML = '<option value="">Seleccione un Usuario</option>';
    usuarioSelect.disabled = true;
    
    limpiarCampos();

    if (edicionSeleccionada in datosEventos) {
      const usuariosEnEdicion = datosEventos[edicionSeleccionada].usuarios;
      for (const usuario in usuariosEnEdicion) {
        const option = document.createElement('option');
        option.value = usuario;
        option.textContent = usuario;
        usuarioSelect.appendChild(option);
      }
      usuarioSelect.disabled = false;
    }
  });

  usuarioSelect.addEventListener('change', () => {
    const edicionSeleccionada = edicionSelect.value;
    const usuarioSeleccionado = usuarioSelect.value;
    
    limpiarCampos();

    if (edicionSeleccionada && usuarioSeleccionado) {
      const infoUsuario = datosEventos[edicionSeleccionada].usuarios[usuarioSeleccionado];
      usuarioHeader.textContent = `Los datos del Registro de ${usuarioSeleccionado} son los siguientes:`;
      fechaRegistroInput.value = infoUsuario.fechaRegistro;
      tipoRegistroInput.value = infoUsuario.tipoRegistro;
      codigoPatrocinioInput.value = infoUsuario.codigoPatrocinio;
    } else {
      usuarioHeader.textContent = 'Los datos del Registro de *Usuario* son los siguientes:';
    }
  });
  
  function limpiarCampos() {
    fechaRegistroInput.value = '';
    tipoRegistroInput.value = '';
    codigoPatrocinioInput.value = '';
    usuarioHeader.textContent = 'Los datos del Registro de *Usuario* son los siguientes:';
  }

});