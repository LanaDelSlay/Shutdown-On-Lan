console.log('is2p2')

function reply_click()
{   var ip;
    ip = document.getElementById('IP').value
  
    
    const ws = new WebSocket("ws://"+ip+":888");
        ws.onopen = () => ws.send('shoutdown');
        ws.onclose = () => alert("Sending shutdown command :)");
        ws.onerror = () => alert("Error");
        console.log('sent message to' + ip)
}