 $(document).ready(function() {

    //Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này
    $("#formCreate").validate({
        rules: {
            city_name: "required",
            country: "required",
            acreage: {
                required: true,
                number:true
            },
            population: {
                required: true,
                number:true
            },
            gdp: {
                required: true,
                number:true
            },
            description: {
                required: true,
                minlength: 2
            },
        },
        messages: {
            city_name: "Please enter city name!",
            country: "Please select country!",
            acreage: {
                required: "Please enter the city area!",
                number: "Please enter only number!"
            },
            population: {
                required: "Please enter the population of the city!",
                number: "Please enter only number!"
            },
            gdp: {
                required: "Please enter the GDP of the city!",
                number: "Please enter only number!"
            },

            description: {
                required: "Please describe some details about the city!",
                minlength: "Please enter at least 2 characters"
            }
        }
    });
});
