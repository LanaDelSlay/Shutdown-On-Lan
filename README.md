<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->

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
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://example.com)

This project is oriented towards shutting down networked local machine with any computer, including phones!

Features:
* Predictive text and persisting saved IPs using client.
* Fully working, Java based WebServer!
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

### Installation

#### Server Setup On Windows

1. Download [this](https://github.com/LanaDelSlay/Shutdown-On-Lan/releases) Extract the server Jar to whatever directory you want!
2. The best way to install the server is to create an automated task using task scheduler. To do this on Windows we'll click the windows key and R at the same time
3. Type in the Run box and click OK/Enter:
   ```sh
   taskschd.msc
   ```
   <img src="https://i.imgur.com/0oawp9G.png" alt="Step 2">
3. On the left side expand the "Task Scheduler Library"
<img src="https://i.imgur.com/s0P0jRP.png" alt="Step 3">
4. On the right side click create basic task 
<img src="https://i.imgur.com/GVVJcKt.png" alt="Step 4">
5. Put whatever name you want in the name box, but select the following settings:
<img src="https://i.imgur.com/p3Bp8h0.png" alt="Step 5">
6. Open the "Triggers" tab
<img src="https://i.imgur.com/ibdZ4d0.png" alt="Step 6">
7.Select At startup for the "Begin the task" box
<img src="https://i.imgur.com/ibdZ4d0.png" alt="Step 6">

#### Running client

Running the client is easy! Just double click the .jar and type in the IP and click shutdown.
<img src="https://i.imgur.com/XkbcIeS.png" alt="Client Example">

Any IPs used will be saved and populate the predictive suggestions next launch.
<img src="https://i.imgur.com/2Nod8CU.png" alt="Predictive Text Example">

#### Connecting to HTTP Server

Type the servers IPv4 + port 8075 into your browser 
```
192.168.0.47::8750
```

Type the server's IP of which you'd like to shut down, then click the button to send the command! 
<img src="https://i.imgur.com/gUG6u0F.png" alt="HTTP Server">

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




<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/LanaDelSlay/Shutdown-On-Lan/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/LanaDelSlay/Shutdown-On-Lan/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/LanaDelSlay/Shutdown-On-Lan/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/LanaDelSlay/Shutdown-On-Lan/issues
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/graham-miller-b655611aa/
[product-screenshot]: images/screenshot.png
