[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/LanaDelSlay/Shutdown-On-Lan/blob/main/corgi.png">
    <img src="Corgi.png" alt="Logo" width="256" height="256">
  </a>

  <h3 align="center">Shutdown On LAN</h3>

  <p align="center">
    A server and client to allow remote shut down, via any device including a phone.
    <br />
    <a href="https://github.com/LanaDelSlay/Shutdown-On-Lan/releases"><strong>Download Now »</strong></a>
    <br />
    <br />
    <a href="https://i.imgur.com/XkbcIeS.png">View Example</a>
    ·
    <a href="https://github.com/LanaDelSlay/Shutdown-On-Lan/issues">Report Bugs</a>
  
</p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
      </ul>
    </li>
    <li><a href="#getting-started">Getting Started</a></li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project
This project is oriented towards shutting down networked local machine with any computer, including phones!

Features:
* Predictive text and persisting saved IPs using client.
* Fully working, Python based WebServer!
* Local HTTP site used to shutdown machines using a phone! :smile:

### Built With

Amazing libraries making my life SO much easier.
* [Jython](https://www.jython.org/)
* [JavaFX](https://openjfx.io/)
* [Apache Commons](https://commons.apache.org/)



<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Software needed
1.) Python
<br />
2.) JDK

### Installation

#### Automatic Server Setup On Windows
1. Download serverInstaller.exe [here](https://github.com/LanaDelSlay/Shutdown-On-Lan/releases)
2. Run it.
3. Click the windows key and R on your keyboard, Type this in the Run box and click OK/Enter:
   ```sh
   taskschd.msc
   ```
   <img src="https://i.imgur.com/0oawp9G.png" alt="Step 3">
4. Click "Task Scheduler Library" on the left hand side, then look for a task called Shutdown Server. If you see it, then it was installed, and you can either run it there using th ebutton on the right hand side, or restart your computer for it to begin starting on stortup.

#### Manual Server Setup On Windows

1. Download [this](https://github.com/LanaDelSlay/Shutdown-On-Lan/releases) Keep the server jar and files in the server folder all together, they need eachother!
2. The best way to install the server is to create an automated task using task scheduler. To do this on Windows we'll click the windows key and R at the same time
3. Type in the Run box and click OK/Enter:
   ```sh
   taskschd.msc
   ```
<img src="https://i.imgur.com/0oawp9G.png" alt="Step 2">
4. On the left side expand and click on the "Task Scheduler Library"
<img src="https://i.imgur.com/s0P0jRP.png" alt="Step 3">
5. On the right side click create basic task 
<img src="https://i.imgur.com/GVVJcKt.png" alt="Step 4">
6. Put whatever name you want in the name box, but select the following settings:
<img src="https://i.imgur.com/p3Bp8h0.png" alt="Step 5">
7. Open the "Triggers" tab
<img src="https://i.imgur.com/ibdZ4d0.png" alt="Step 6">
8. Select At startup for the "Begin the task" box
<img src="https://i.imgur.com/sG6TkeB.png" alt="Step 7">
9. Switch to the "Actions" tab, the the right of Triggers
<img src="https://i.imgur.com/dX5CnKV.png" alt="Step 8">
10. Click the New button at the bottom, and leave the action on "Start a program". Click browse and browse to the startRebootServer.bat included in the release. 
<img src="https://i.imgur.com/5IfIYpg.png" alt="Step 9">
11. You're all done! Now to test if it's working use open client and type in the servers IP and use the test connection button. Or connect via your web browser, by typing the ipv4 postfixed with 8750. Check <a href="#connecting-to-http-server">this</a> for more info 

#### Running client
Running the client is easy! Just double click the .jar and type in the IP and click shutdown.

<img src="https://i.imgur.com/XkbcIeS.png" alt="Client Example">

Any IPs used will be saved and populate the predictive suggestions next launch.

<img src="https://i.imgur.com/2Nod8CU.png" alt="Predictive Text Example">

#### Connecting to HTTP Server
Type the servers IPv4 + port 8075 into your browser 
```
192.168.0.47:8750
```
Type the server's IP of which you'd like to shut down, then click the button to send the command! (or leave empty to shutdown local machine)
<img src="https://i.imgur.com/LQkQP6T.png" alt="HTTP Server">

<!-- USAGE EXAMPLES -->
## Usage

Do whatever you want with this, I do not care :) (Dont hurt people, though)

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request


<!-- CONTACT -->
## Contact

Graham - gmiller1902@gmail.com

Project Link: [https://github.com/LanaDelSlay/Shutdown-On-Lan/](https://github.com/LanaDelSlay/Shutdown-On-Lan/)

<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements
* [Java-WebSocket](https://github.com/TooTallNate/Java-WebSocket)
* [Hyperspeed JS Background](https://fdossena.com/?p=warpspeed/i.frag)



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/LanaDelSlay/Shutdown-On-Lan.svg?style=for-the-badge
[contributors-url]: https://github.com/LanaDelSlay/Shutdown-On-Lan/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/LanaDelSlay/Shutdown-On-Lan.svg?style=for-the-badge
[forks-url]: https://github.com/LanaDelSlay/Shutdown-On-Lan/network/members
[stars-shield]: https://img.shields.io/github/stars/LanaDelSlay/Shutdown-On-Lan.svg?style=for-the-badge
[stars-url]: https://github.com/LanaDelSlay/Shutdown-On-Lan/stargazers
[issues-shield]: https://img.shields.io/github/issues/LanaDelSlay/Shutdown-On-Lan.svg?style=for-the-badge
[issues-url]: https://github.com/LanaDelSlay/Shutdown-On-Lan/issues
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/graham-miller-b655611aa/
[product-screenshot]: images/screenshot.png
