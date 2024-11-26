document.addEventListener("DOMContentLoaded", function () {
    const currentLocation = window.location.href;
    const menuLinks = document.querySelectorAll("nav a");

    menuLinks.forEach(link => {
        if (link.href === currentLocation) {
            link.classList.add("active");
        }
    });
});