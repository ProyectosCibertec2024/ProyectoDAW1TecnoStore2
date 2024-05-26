const body = document.querySelector('body');
const sidebar = document.querySelector('nav.sidebar');
const toggle = document.querySelector('.toggle');
const main = document.querySelector('main');
const modeSwitch = document.querySelector('.toggle-switch');
const modeText = document.querySelector('.mode-text');

toggle.addEventListener('click', () => {
    sidebar.classList.toggle('close');
    main.classList.toggle('menu-close');

    if (sidebar.classList.contains('close')) {
        // Si el menú está cerrado, ajusta el margen izquierdo del contenido principal
        // a su valor inicial (80px) con una transición
        main.style.transition = 'margin-left 0.3s ease';
        main.style.marginLeft = '120px';
    } else {
        // Si el menú está abierto, ajusta el margen izquierdo del contenido principal
        // para que el menú no se superponga y ocupe todo el ancho de la pantalla
        // con una transición
        main.style.transition = 'margin-left 0.3s ease';
        main.style.marginLeft = '300px';
    }
});

modeSwitch.addEventListener('click', () => {
    body.classList.toggle('dark');
    modeText.innerText = body.classList.contains('dark') ? 'Light mode' : 'Dark mode';
});