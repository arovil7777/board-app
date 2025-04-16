// custom.js
// 페이지 로드 시 알림 메시지를 자동으로 숨기는 등의 간단한 스크립트
document.addEventListener("DOMContentLoaded", function () {
    const alert = document.querySelector('.alert');
    if (alert) {
        setTimeout(() => {
            alert.style.display = 'none';
        }, 3000); // 3초 후에 알림 숨김
    }
});