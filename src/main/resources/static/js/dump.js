function changeMemberSize(size) {
    let currentUrl = new URL(window.location.href);
    let name = currentUrl.searchParams.get("name");
    let newUrl = '/members?page=0&size=' + size;
    if (name) {
        newUrl += '&name=' + encodeURIComponent(name);
    }
    window.location.href = newUrl;
}

function changeEquipmentSize(size) {
    let currentUrl = new URL(window.location.href);
    let type = currentUrl.searchParams.get("type");
    let newUrl = '/equipment?page=0&size=' + size;
    if (type) {
        newUrl += '&type=' + encodeURIComponent(type);
    }
    window.location.href = newUrl;
}

function changeMaintenanceSize(size) {
    window.location.href = '/maintenance?page=0&size=' + size;
}

function changePaymentSize(size) {
    window.location.href = '/payment?page=0&size=' + size;
}

