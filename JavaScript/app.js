function reply_click()
{   var ip;
    ip = document.getElementById('IP').value
  
    
    const ws = new WebSocket("ws://"+ip+":888");
        ws.onopen = () => ws.send('shutdown');
        ws.onclose = () => alert("Sending shutdown command :)");
        ws.onerror = () => alert("Error - Please refresh");
        console.log('sent message to' + ip)
}