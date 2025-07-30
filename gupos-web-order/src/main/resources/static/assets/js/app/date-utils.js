function getDateRange(months) {
    const currentDate = new Date();
    const endDate = currentDate.toISOString().split('T')[0]; // 오늘 날짜 (YYYY-MM-DD)

    const startDate = new Date(currentDate);
    startDate.setMonth(startDate.getMonth() - months);

    // 윤년을 고려하여 시작일과 종료일을 설정
    const formattedStartDate = formatDate(startDate);
    const formattedEndDate = formatDate(currentDate);

    return {
        startDate: formattedStartDate,
        endDate: formattedEndDate
    };
}

// 날짜를 YYYY-MM-DD 형식으로 포맷하는 함수
function formatDate(date) {
    const year = date.getFullYear();
    let month = date.getMonth() + 1; // getMonth()는 0부터 시작하므로 1을 더해줌
    let day = date.getDate();

    // 월과 일이 한 자리 수인 경우 앞에 0을 추가하여 두 자리로 만듦
    month = month < 10 ? '0' + month : month;
    day = day < 10 ? '0' + day : day;

    return `${year}-${month}-${day}`;
}