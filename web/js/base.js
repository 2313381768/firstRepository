$(".my-center").click(function() {
    $(".menu-my").show({
        duration: 500,
    })
    $("#center-id").addClass("change-i")
});
$(".my-center").mouseleave(
    function() {
        $(".menu-my").hide({
            duration: 500,
        })
        $("#center-id").removeClass("change-i",500)

    }
);
$(".sell").click(function() {
    $(".sell-menu").show({
        duration: 500,
    })
    $("#sell-id").addClass("change-i")
});
$(".sell").mouseleave(
    function() {
        $(".sell-menu").hide({
            duration: 500,
        })
        $("#sell-id").removeClass("change-i",500)
    }
);