// Ajax JQuery

// Khi nào nội dung trang html (document) đã được load vào trình duyệt
// thì sẽ chạy code bên trong function
$(document).ready(function(){
// Lắng nghe sự kiện click cho thẻ có id là btn-delete-user
    $(".btn-delete-user").click(function(){
        // this: đại diện cho sự kiện xóa khi bấm vào btn
        var id = $(this).attr("userid")
        var This = $(this)
        $.ajax({
          method: "GET",
          url: "http://localhost:8080/demoservlet/user/delete?id=" + id,
          // data: { name: "John", location: "Boston" } - Dành cho "POST"
        })
          .done(function( result ) {
          // This.closest(): Chỉ định thẻ, id, class
            This.closest("tr").remove();
            console.log("Ket qua",result)

          });
    })
})