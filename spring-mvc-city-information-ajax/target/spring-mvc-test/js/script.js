function editCity(){
    let cityId = $(this).attr("value");
    $.ajax({
        type: "GET",
        url: `/cities/edit-city/${cityId}`,
        success:function (city){
            $('#upID').val(city.city_id);
            $('#upName').val(city.city_name);
            $('#upAcreage').val(city.acreage);
            $('#upPopulation').val(city.population);
            $('#upGDP').val(city.gdp);
            $('#upDescription').val(city.description);
            $('#upCountry').val(city.country.country_id);
            $('#editModal').modal('show');

        }})
    // }).done(function (city){
    //     $('#upID').val(city.city_id);
    //     $('#upName').val(city.city_name);
    //     $('#upAcreage').val(city.acreage);
    //     $('#upPopulation').val(city.population);
    //     $('#upGDP').val(city.gdp);
    //     $('#upDescription').val(city.description);
    //     $('#upCountry').val(city.country.country_id);
    //     $('#editModal').modal('show');
    // }).fail(function (){
    //     App.showErrorAlert("Something went wrong!")
    // })
}

function saveCity(){
    let city_id = $("#upID").val();
    let city_name = $("#upName").val();
    let acreage = $("#upAcreage").val();
    let population = $("#upPopulation").val();
    let gdp = $("#upGDP").val();
    let description = $("upDescription").val();
    let country = $("upCountry").val();
    let newCountry = {
        country_id : country
    }

    let city = {
        city_id: city_id,
        city_name: city_name,
        acreage: acreage,
        population: population,
        gdp: gdp,
        description:description,
        country: newCountry
    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        data: JSON.stringify(city),
        url: `/cities/edit/${city_id}`,
        success: function (product) {
            $('#row'+city_id+ ' td').remove();
            $('#row'+city_id).html(`
                        <td th:text="${city.city_id}"></td>
                        <td th:text="${city.city_name}"></td>
                        <td th:if="${city.country}" th:text="${city.country.country_name}"></td>
                        <td th:unless="${city.country}"></td>
                        <td>
                            <button value="${city.city_id}" class="btn btn-outline-primary mr-2 edit-button" ><i class="far fa-edit"></i>Edit</button>
                            <button value="${city.city_id}" class="btn btn-outline-danger delete-button" ><i class="fas fa-trash-alt"></i>Delete</button>
                        </td>`
            );
            $(".edit-button").on("click",editCity);
            $(".delete-button").on("click",deleteConfirm);
            $('#editModal').modal("hide");
            App.showSuccessAlert("Edit product successfully!")
            $("#edit-city")[0].reset();
        }
    })
}

function deleteConfirm() {
    let cityId = $(this).attr("value");
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showDenyButton: true,
        confirmButtonColor: '#3085d6',
        denyButtonColor: '#d33',
        denyButtonText :`Cancel`,
        confirmButtonText: 'Yes, hidden it!'
    }).then((result) => {
        if (result.isConfirmed) {
            //Gọi ajax
            $.ajax({
                type: "DELETE",
                //tên API
                url: `/cities/${cityId}`,
                //xử lý khi thành công
                success: function () {
                    console.log("abc");
                    // $(`#row${data.id}`).html("");
                    $("#row" + cityId).remove();

                    Swal.fire(
                        'Hidden!',
                        'Your file has been hidden.',
                        'success'
                    )
                }
            })
        }
    })
}

$(".edit-button").on("click",editCity);
$(".save-city").on("click",saveCity);
$(".delete-button").on("click",deleteConfirm);