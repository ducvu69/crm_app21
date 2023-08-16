$(document).ready(function(){
// Lắng nghe sự kiện click cho thẻ có id là btn-delete-user
    $(".btn-delete-role").click(function(){
        // this: đại diện cho sự kiện xóa khi bấm vào btn
        var id = $(this).attr("roleid")
        var This = $(this)
        $.ajax({
          method: "GET",
          url: "http://localhost:8080/demoservlet/role/delete?id=" + id,
          // data: { name: "John", location: "Boston" } - Dành cho "POST"
        })
          .done(function( result ) {
          // This.closest(): Chỉ định thẻ, id, class
            This.closest("tr").remove();
            console.log("Ket qua",result)

          });
    })
})