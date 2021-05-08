function reply_click()
{   var ip;
    ip = document.getElementById('IP').value

    //If no input, select local host by using 0.0.0.0
    if(ip) {
    } else {
        ip = "0.0.0.0";
    }
  
    
    const ws = new WebSocket("ws://"+ip+":888");
        ws.onopen = () => ws.send('shutdown');
        ws.onclose = () => alert("Sending shutdown command :)");
        ws.onerror = () => alert("Error - Please refresh");
        console.log('sent message to' + ip)
}
