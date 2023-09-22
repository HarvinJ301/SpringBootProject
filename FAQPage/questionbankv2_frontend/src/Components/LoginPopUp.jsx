import "../Styles/LoginPopUp.css";
import {FaWindowClose} from "react-icons/fa"

const LoginPopUp = (props) => {

return (props.trigger) ? (
<div className = "loginpopup">
    <div className = "loginpopup-inner">
        <button className="close-btn" onClick={() => props.setTrigger(false)}><FaWindowClose/></button>
        { props.children }
    </div>
</div>
    ) : "";
}

export default LoginPopUp;