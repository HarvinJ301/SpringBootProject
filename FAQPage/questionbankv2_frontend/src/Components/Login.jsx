import { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom";
import axios from "axios";
import '../Styles/Login.css'

const Login = () =>{

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [tempUser, setTempUser] = useState("");
    const [invalidUser, setInvalidUser] = useState(false)
    const [invalidPassword, setInvalidPassword] = useState(false)
    const [detailsMatch, setDetailsMatch] = useState(true)

    const navigate = useNavigate();

    const api = "http://localhost:8088/api/v1/user/username/"


    const [loading, setLoading] = useState(false);

    useEffect(() => {
      if (username === "") {
        return;
      }
    
      setLoading(true);
      axios.get(api + username)
        .then((response) => {
          setTempUser(response.data);
          setLoading(false);
    
          if (response.data === "") {
            setInvalidUser(true);
          } else {
            setInvalidUser(false);
          }
        })
        .catch((error) => {
          console.error(error);
          setInvalidUser(true);
          setLoading(false);
        });
    }, [username]);
    
    const handleSubmit = (event) => {
      event.preventDefault();
    
      if (!tempUser) {
        return;
      }
    
      if (password === tempUser.password) {
        localStorage.setItem("user", JSON.stringify(tempUser));
        setInvalidPassword(false);
        navigate("/questions");
        window.location.reload(false);
      } else {
        passwordChecker();
        setDetailsMatch(false);
      }
    };

    const passwordChecker = () =>{

        if(password === ""){
            setInvalidPassword(true)
        }
        else{
            setInvalidPassword(false)
        }

        

    }


    return(
        <div className="login-container">
            <h2>Login</h2>
            <form onSubmit={handleSubmit} className="login-form">
                <div className="input-container">
                    <label>Username:</label>
                    <input name="username" type="text" placeholder="Username" value={username} onChange={(event) => setUsername(event.target.value)}/>
                </div>
                <div className="input-container">
                    <label>Password:</label>
                    <input name="password" type="password" placeholder="Password" value={password} onChange={(event) => setPassword(event.target.value)}/>
                    {
                        invalidPassword && <span className="errorMessage">Please make sure your password is valid</span>
                    }
                </div>
                {
                    !detailsMatch && <span className="errorMessage">Please check username and password are correct</span>
                }
                <input type="submit" value="Login"/>
            </form>
    </div>
    )
}
export default Login
