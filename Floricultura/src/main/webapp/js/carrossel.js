let indiceSlide = 0;

function mudarSlide(direcao) {
    const slides = document.querySelector('.slides');
    const totalSlides = document.querySelectorAll('.slides img').length;
    const larguraSlide = document.querySelector('.carrossel').offsetWidth;

    indiceSlide += direcao;

    if (indiceSlide >= totalSlides) {
        indiceSlide = 0;
    }
    
    if (indiceSlide < 0) {
        indiceSlide = totalSlides - 1;
    }

    slides.style.transition = "transform 0.5s ease-in-out";
    slides.style.transform = `translateX(-${indiceSlide * larguraSlide}px)`;

    atualizarIndicadores(indiceSlide);
}

function atualizarIndicadores(indice) {
    const indicadores = document.querySelectorAll('.indicador');
    indicadores.forEach((indicador, index) => {
        if (index === indice) {
            indicador.classList.add('ativo');
        } else {
            indicador.classList.remove('ativo');
        }
    });
}

function criarIndicadores() {
    const totalSlides = document.querySelectorAll('.slides img').length;
    const indicadoresContainer = document.querySelector('.indicadores');

    for (let i = 0; i < totalSlides; i++) {
        const indicador = document.createElement('span');
        indicador.classList.add('indicador');
        indicador.addEventListener('click', () => mudarSlideManual(i));
        indicadoresContainer.appendChild(indicador);
    }
    atualizarIndicadores(indiceSlide);
}

function mudarSlideManual(indice) {
    indiceSlide = indice;
    const slides = document.querySelector('.slides');
    const larguraSlide = document.querySelector('.carrossel').offsetWidth;
    slides.style.transform = `translateX(-${indiceSlide * larguraSlide}px)`;
    atualizarIndicadores(indiceSlide);
}

document.addEventListener('DOMContentLoaded', criarIndicadores);

setInterval(() => {
    mudarSlide(1);
}, 5000); 