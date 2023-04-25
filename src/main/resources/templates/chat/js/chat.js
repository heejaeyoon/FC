const url = "http://localhost:8077";
let stompClient;
let selectedUser;

function connectToChat(userName){
    console.log("connecting to chat");
    let socket = new SockJS(url + '/chat' + userName);  //chat 컨트롤러에서 @MessageMapping("")경로
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame){
        console.log(' connected to : '+ frame);
        /* chat 컨트롤러에서 simpMessageingTemplate의 목적지*/
        stompClient.subscribe("/topic/messages/"+ userName, function (response){
            let data = JSON.parse(response.body);
            console.log(data);

        })
    })
}

function sendMsg(from, text) {
    stompClient.send("/app/chat/" + selectedUser, {}, JSON.stringify({
        formLogin : from,
        message : text

    }));
}

/*botton onclick = "registeration()"*/
function registeration(){
    let userName = document.querySelector("#userName").value;
    $.get(url+"/registeration/" + userName, function(response) {
        connectToChat(userName);

    }).fail(function(error) {
        if(error.status === 400){
            alert("로그인 중 입니다.");
        }
    })

}

function fetchAll (){

    $.get(url+"/fetchAllUsers/", function(response) {
        let users = response;
        let userTemplate = "";
        for(let i = 0; i< users.length; i++){

        }

    })
}