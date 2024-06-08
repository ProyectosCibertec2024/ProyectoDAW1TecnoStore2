const body = document.querySelector('body');
const sidebar = document.querySelector('nav.sidebar');
const toggle = document.querySelector('.toggle');
const main = document.querySelector('main');
const modeSwitch = document.querySelector('.toggle-switch');
const modeText = document.querySelector('.mode-text');

// Verifica el estado del sidebar guardado en localStorage
if (localStorage.getItem('sidebarState') === 'open') {
    sidebar.classList.remove('close');
    main.classList.remove('menu-close');
    main.style.marginLeft = '300px';
} else {
    sidebar.classList.add('close');
    main.classList.add('menu-close');
    main.style.marginLeft = '120px';
}

// Verifica el estado del modo oscuro guardado en localStorage
if (localStorage.getItem('darkMode') === 'enabled') {
    body.classList.add('dark');
    modeText.innerText = 'Light mode';
} else {
    body.classList.remove('dark');
    modeText.innerText = 'Dark mode';
}

toggle.addEventListener('click', () => {
    sidebar.classList.toggle('close');
    main.classList.toggle('menu-close');

    if (sidebar.classList.contains('close')) {
        main.style.transition = 'margin-left 0.3s ease';
        main.style.marginLeft = '120px';
        localStorage.setItem('sidebarState', 'closed');
    } else {
        main.style.transition = 'margin-left 0.3s ease';
        main.style.marginLeft = '300px';
        localStorage.setItem('sidebarState', 'open');
    }
});

modeSwitch.addEventListener('click', () => {
    body.classList.toggle('dark');

    if (body.classList.contains('dark')) {
        modeText.innerText = 'Light mode';
        localStorage.setItem('darkMode', 'enabled');
    } else {
        modeText.innerText = 'Dark mode';
        localStorage.setItem('darkMode', 'disabled');
    }
});