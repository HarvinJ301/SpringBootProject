import '../Styles/Footer.css'
import { FaLinkedin } from 'react-icons/fa';
import { AiFillGithub } from "react-icons/ai";


const Footer = () =>{
    return(
        <div className="footer">
            <ul class="socials">
            <li><a href="https://www.linkedin.com/in/harvin-johal/"><FaLinkedin/></a></li>
            <li><a href="https://github.com/HarvinJ301"><AiFillGithub/></a></li>
            <li><a href="d:\CV website stuff\CV updated to white.pdf" download>CV</a></li>
            </ul>

        </div>
    )
}
export default Footer