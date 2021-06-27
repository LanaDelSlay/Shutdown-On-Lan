function shutdown_click()
{   var ip;
    ip = document.getElementById('IP').value

    //If no input, select local host
    if(ip) {

    } else {

    var ip = location.host;
    var n = ip.indexOf(":");
    var ip = ip.substr(0,n)
    //Removes port and colon. 
    }
  
    
    const ws = new WebSocket("ws://"+ip+":888");
        ws.onopen = () => ws.send('shutdown');
        ws.onclose = () => alert("Sending shutdown command :)");
        ws.onerror = () => alert("Error - Please refresh");
        console.log('sent message to' + ip)
}

function sleep_click(){
    var ip;
    ip = document.getElementById('IP').value

    //If no input, select local host
    if(ip) {

    } else {

    var ip = location.host;
    var n = ip.indexOf(":");
    var ip = ip.substr(0,n)
    //Removes port and colon. 
    }

    const ws = new WebSocket("ws://"+ip+":888");
    ws.onopen = () => ws.send('sleep');
    ws.onclose = () => alert("Sending sleep command :)");
    ws.onerror = () => alert("Error - Please refresh");
    console.log('sent message to' + ip)

}