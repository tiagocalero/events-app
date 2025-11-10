(function () {
  const input = document.getElementById('nickname');
  const statusEl = document.getElementById('nick-status');
  const submitBtn = document.getElementById('submitBtn');

  if (!input || !statusEl) return;

  const endpoint = window.CHECK_NICK_URL || '/webdinamica/check-nickname';
  const re = /^[A-Za-z0-9_]{3,20}$/;

  let controller = null;
  let lastValue = '';

  function debounce(fn, delay) {
    let t;
    return function (...args) {
      clearTimeout(t);
      t = setTimeout(() => fn.apply(this, args), delay);
    };
  }

  function setStatus(text, state) {
    statusEl.textContent = text || '';
    statusEl.className = 'hint ' + (state || '');
    if (state === 'ok') {
      statusEl.style.cssText = 'color:#166534;background:#ecfdf5;border:1px solid #86efac;border-radius:4px;padding:2px 6px;';
    } else if (state === 'err') {
      statusEl.style.cssText = 'color:#991b1b;background:#fef2f2;border:1px solid #fecaca;border-radius:4px;padding:2px 6px;';
    } else if (state === 'pending') {
      statusEl.style.cssText = 'color:#374151;background:#f3f4f6;border:1px solid #e5e7eb;border-radius:4px;padding:2px 6px;';
    } else {
      statusEl.style.cssText = '';
    }
  }

  async function checkNickname(value) {
    // Cancela la petición anterior si sigue en vuelo
    if (controller) controller.abort();
    controller = new AbortController();

    try {
      setStatus('Comprobando disponibilidad...', 'pending');
      submitBtn && (submitBtn.disabled = true);

      const res = await fetch(`${endpoint}?nickname=${encodeURIComponent(value)}`, {
        method: 'GET',
        headers: { 'Accept': 'application/json' },
        cache: 'no-store',
        signal: controller.signal
      });

      if (!res.ok) {
        setStatus('No se pudo verificar en este momento. Intenta nuevamente.', 'err');
        submitBtn && (submitBtn.disabled = false);
        return;
      }

      const data = await res.json(); // { available: boolean, message: string }
      if (data.available) {
        setStatus(data.message || 'Disponible', 'ok');
        submitBtn && (submitBtn.disabled = false);
      } else {
        setStatus(data.message || 'No disponible', 'err');
        submitBtn && (submitBtn.disabled = true);
      }
    } catch (e) {
      if (e.name === 'AbortError') return; // Ignorar aborts por tipeo
      setStatus('Error de red. Reintenta.', 'err');
      submitBtn && (submitBtn.disabled = false);
    }
  }

  const onInput = debounce(function (e) {
    const value = e.target.value.trim();
    lastValue = value;

    if (value.length === 0) {
      setStatus('');
      submitBtn && (submitBtn.disabled = false);
      return;
    }

    // Validación rápida en cliente
    if (!re.test(value)) {
      setStatus('Usa 3-20 caracteres: letras, números o _.', 'err');
      submitBtn && (submitBtn.disabled = true);
      return;
    }

    checkNickname(value);
  }, 300);

  input.addEventListener('input', onInput);
})();