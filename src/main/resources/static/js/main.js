$(function(){

    $('.send-student').on('click', function(){
      let name = $('.name-student').val();
      let birthdate = $('.birthdate-student').val();
      let group = $('.group-student').val();
      $.post('/api/v1/storage', {name: name, birthdate: birthdate, group: group}, function(response){
        if(response != 0){
          $('.name-student').val('');
          $('.birthdate-student').val('');
          $('.group-student').val('');
          return;
        } else {
          alert('error');
        }
      });
    });
});