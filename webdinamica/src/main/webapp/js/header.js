(function () {
  if (window.__userSuggestBound) return;
  window.__userSuggestBound = true;

  const DEBOUNCE_MS = 250;
  const MIN_LEN = 2;

  function ready(fn) {
    if (document.readyState === 'loading') document.addEventListener('DOMContentLoaded', fn);
    else fn();
  }

  function debounce(fn, delay) {
    let t;
    return function (...args) {
      clearTimeout(t);
      t = setTimeout(() => fn.apply(this, args), delay);
    };
  }

  ready(() => {
    const input = document.getElementById('userSuggestInput');
    const menu = document.getElementById('userSuggestResults');
    if (!input || !menu) return;

    let items = [];
    let activeIndex = -1;

    async function suggest(q) {
      if (q.length < MIN_LEN) { hide(); return; }
      // Build an absolute URL using the data attribute (recommended)
      let url = (input.dataset.suggestUrl || '').trim();
      if (!url) {
        // Fallback to context-root heuristic
        const p = window.location.pathname;
        const i = p.indexOf('/', 1);
        const ctx = i === -1 ? '' : p.substring(0, i);
        url = ctx + '/userSuggest';
      }
      const params = new URLSearchParams({ q, limit: '8' });
      try {
        const resp = await fetch(`${url}?${params.toString()}`, {
          headers: { 'Accept': 'application/json' },
          credentials: 'same-origin'
        });
        if (!resp.ok) { hide(); return; }
        const data = await resp.json();
        render(Array.isArray(data.results) ? data.results : []);
      } catch {
        hide();
      }
    }

    function render(list) {
      items = list;
      activeIndex = -1;
      menu.innerHTML = '';
      if (!items.length) { hide(); return; }

      items.forEach((nick, idx) => {
        const a = document.createElement('a');
        a.className = 'dropdown-item result-item';
        a.setAttribute('role', 'option');
        a.id = `user-suggest-${idx}`;
        a.textContent = nick;
        // Use mousedown to prevent blur before click
        a.addEventListener('mousedown', (e) => {
          e.preventDefault();
          select(idx);
        });
        menu.appendChild(a);
      });

      show();
      // Size and position relative to input (without Popper)
      const r = input.getBoundingClientRect();
      menu.style.width = r.width + 'px';
    }

    function show() {
      // Bootstrap: use .show class; avoid [hidden] or .d-none
      menu.classList.add('show');
      menu.classList.remove('d-none', 'invisible');
      menu.style.visibility = 'visible';
      menu.style.opacity = '1';
    }

    function hide() {
      menu.classList.remove('show');
      // Keep it simple: no hidden attribute to avoid Bootstrap’s [hidden]{display:none!important}
      menu.style.visibility = 'hidden';
      menu.style.opacity = '0';
      menu.innerHTML = '';
      items = [];
      activeIndex = -1;
    }

	function highlight(idx) {
	  const itemsEls = menu.querySelectorAll('.dropdown-item'); // or '.result-item' if you didn’t use dropdown-item
	  itemsEls.forEach((el, i) => {
	    const isActive = i === idx;
	    el.classList.toggle('active', isActive);           // Bootstrap active state
	    el.setAttribute('aria-selected', isActive ? 'true' : 'false'); // A11y
	  });
	}

    function select(idx) {
      if (idx < 0 || idx >= items.length) return;
      const nick = items[idx];
      input.value = nick;
      hide();
      const tpl = input.dataset.profileTemplate || '/webdinamica/usuarios/consulta?nick={nickname}';
      const target = tpl.replace('{nickname}', encodeURIComponent(nick));
      window.location.href = target;
    }

    function scrollIntoView(idx) {
      const el = document.getElementById('user-suggest-' + idx);
      if (!el) return;
      const parent = menu;
      const elTop = el.offsetTop;
      const elBottom = elTop + el.offsetHeight;
      const viewTop = parent.scrollTop;
      const viewBottom = viewTop + parent.clientHeight;
      if (elTop < viewTop) parent.scrollTop = elTop;
      else if (elBottom > viewBottom) parent.scrollTop = elBottom - parent.clientHeight;
    }

    input.addEventListener('input', debounce(e => {
      suggest(e.target.value.trim());
    }, DEBOUNCE_MS));

    input.addEventListener('keydown', (e) => {
      if (menu.classList.contains('show')) {
        const max = items.length - 1;
        if (e.key === 'ArrowDown') {
          e.preventDefault();
          activeIndex = activeIndex < max ? activeIndex + 1 : 0;
          highlight(activeIndex);
          scrollIntoView(activeIndex);
          return;
        } else if (e.key === 'ArrowUp') {
          e.preventDefault();
          activeIndex = activeIndex > 0 ? activeIndex - 1 : max;
          highlight(activeIndex);
          scrollIntoView(activeIndex);
          return;
        } else if (e.key === 'Enter' && activeIndex >= 0) {
          e.preventDefault();
          select(activeIndex);
          return;
        } else if (e.key === 'Escape') {
          hide();
          return;
        }
      }
      if (e.key === 'Enter') {
        const nick = input.value.trim();
        if (nick) {
          const tpl = input.dataset.profileTemplate || '/webdinamica/usuarios/consulta?nick={nickname}';
          window.location.href = tpl.replace('{nickname}', encodeURIComponent(nick));
        }
      }
    });

    document.addEventListener('click', (e) => {
      if (!menu.contains(e.target) && e.target !== input) hide();
    });

    // Keep menu aligned on scroll/resize (no Popper)
    window.addEventListener('scroll', () => {
      if (!menu.classList.contains('show')) return;
      const r = input.getBoundingClientRect();
      menu.style.width = r.width + 'px';
    }, true);
    window.addEventListener('resize', () => {
      if (!menu.classList.contains('show')) return;
      const r = input.getBoundingClientRect();
      menu.style.width = r.width + 'px';
    });
  });
})();