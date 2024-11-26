document.addEventListener("DOMContentLoaded", function () {
    const inputs = document.querySelectorAll('input');
    
    inputs.forEach(input => {
        if (input.value === "null" || input.value === null) {
            input.value = "";
        }
    });
});