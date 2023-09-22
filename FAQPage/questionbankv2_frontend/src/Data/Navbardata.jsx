import React from 'react'
import * as AiIcons from 'react-icons/ai'
import * as BsIcons from 'react-icons/bs'


export const Navbardata = [
    {
        title: "Home",
        icon: <AiIcons.AiFillHome/>,
        link: "/home"
    },
    {
        title: "Questions",
        icon: <BsIcons.BsFillPatchQuestionFill/>,
        link: "/questions"
    },
    {
        title: "Favourites",
        icon: <AiIcons.AiFillStar/>,
        link: "/favourites"
    },
    {
        title: "Settings",
        icon: <AiIcons.AiFillSetting/>,
        link: "/settings"
    },
    {
        title: "Logout",
        icon: <AiIcons.AiOutlineLogout/>,
        link: "/logout"
    }



]