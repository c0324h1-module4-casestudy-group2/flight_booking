new WOW().init();

flatpickr(".date-flatpickr", {
    dateFormat: 'd-m-Y',
    minDate: 'today',
    maxDate: new Date().fp_incr(14)
});


// Cuộn trang lên đầu khi nhấn nút
$('scrollToTop').click(() => {
    window.scrollTo({
        top: 0,
        behavior: 'smooth'
    });
});

$('.failed').click(() => {
    Swal.fire({
        icon: "warning",
        iconColor: "#ffc107",
        title: "Tính năng đang phát triển!",
        confirmButtonText: "Đóng",
        color: "#003366"
    })
})

document.addEventListener('DOMContentLoaded', function () {
    new Swiper('.swiper-container', {
        slidesPerView: 4, // Hiển thị 4 thẻ cùng một lúc
        spaceBetween: 10,
        loop: true,
        autoplay: {
            delay: 3000, // Tự động trượt sau mỗi 3 giây
            disableOnInteraction: false,
        },
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        breakpoints: {
            640: {
                slidesPerView: 1,
                spaceBetween: 20,
            },
            768: {
                slidesPerView: 2,
                spaceBetween: 30,
            },
            1024: {
                slidesPerView: 4,
                spaceBetween: 40,
            },
        }
    });
});

document.addEventListener('DOMContentLoaded', function () {
// xử lý select dropdown
    document.querySelectorAll('.departure-item').forEach(el => {
        el.addEventListener('click', event => {
            const airportCity = el.querySelector(".city-departure").textContent;
            const inputDisplay = document.getElementById('departure');
            const airportId = el.getAttribute('data-value');
            const inputId = document.getElementById('departureAirportId');
            inputDisplay.value = airportCity;
            inputId.value = airportId;
        });
    });
    document.querySelectorAll('.arrival-item').forEach(el => {
        el.addEventListener('click', event => {
            const airportCity = el.querySelector(".city-arrival").textContent;
            const inputDisplay = document.getElementById('arrival');
            const airportId = el.getAttribute('data-value');
            const inputId = document.getElementById('arrivalAirportId');
            inputDisplay.value = airportCity;
            inputId.value = airportId;
        });
    });
});