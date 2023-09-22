import React from 'react'
import { Link } from 'react-router-dom'
import * as FaIcons from 'react-icons/fa'
import * as IoIcons from 'react-icons/io'
import { useRef } from 'react'
import "../Styles/NewNavbar.css"
import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'

const Navbar = () => {
  const navRef = useRef();

  const showNavbar = () => {
    navRef.current.classList.toggle("responsive_nav");
  }

  const [isLoggedin, setIsLoggedIn] = useState(false)
  const [accountCheck, setAccountCheck] = useState(false)
  const [user, setUser] = useState({})

  
  const navigate = useNavigate();


  const handleLogout = () => {
      localStorage.clear();
      navigate("/")
      window.location.reload(false)
      };

  const checkLoggedIn =  ( )=>{
      const loggedInUser = localStorage.getItem("user");

      if (loggedInUser) {
          const foundUser = JSON.parse(loggedInUser);
          setIsLoggedIn(true)
          if (foundUser.userType === "admin"){
              setAccountCheck(true)
          }
          setUser(foundUser)
      }
      else{
          setIsLoggedIn(false)
      }
  }

  useEffect(()=>{
      checkLoggedIn()
  }, [])


  return (
    <header>
        <nav ref={navRef}>
            <Link className="link" to="/questions">Questions</Link>
            <Link className="link" to="/addquestion">Add Question</Link>
                    {
                    isLoggedin  
                    ?<Link className="link" onClick={handleLogout}><IoIcons.IoIosLogOut/> Sign Out</Link>
                    :<Link className="link" to="/">Login</Link>
                    }
            <button className="nav-btn nav-close-btn" onClick={showNavbar}>
                <FaIcons.FaTimes/>
            </button>
        </nav>
        <button className="nav-btn" onClick={showNavbar}>
            <FaIcons.FaBars/>
        </button>
    </header>
  )
}

export default Navbar