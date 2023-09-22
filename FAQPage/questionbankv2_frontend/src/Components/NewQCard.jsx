import "../Styles/NewQCard.css";
import { useState, useEffect } from "react";
import DeletePopUp from "./DeletePopUp";


const NewQCard = (props) => {
  const [isLoggedin, setIsLoggedIn] = useState(false);
  const [accountCheck, setAccountCheck] = useState(false);
  const [user, setUser] = useState({});
  const [admin, setAdmin] = useState(true);
  const [deletePopUp, setDeletePopUp] = useState({ show: false });

  const [answer, setAnswer] = useState(true);

  const {
    questionID,
    league,
    footballTeam,
    askedBy,
    teamUserWhoAskedSupports,
    questionAsked,
    keywords,
    questionAnswer
  } = props.question;

  function HandleEdit() {
    props.edit(props.question);
  }
  //when they click the initial delete button
  const HandleDelete = () => {
    setDeletePopUp({
      show: true,
    });
  };
  //the user clicks delete on the pop up.
  const handleDeleteTrue = () => {
    props.delete(questionID);
    alert("Question has been deleted.");
  };

  //when they click no from the delete pop up
  const handleDeleteFalse = () => {
    setDeletePopUp({
      show: false,
    });
  };

  const handleReveal = () => {
    if (answer === true) {
      setAnswer(false);
    } else if (answer === false) {
      setAnswer(true);
    }
  };

  const checkLoggedIn = () => {
    const loggedInUser = localStorage.getItem("user");

    if (loggedInUser) {
      const foundUser = JSON.parse(loggedInUser);
      setIsLoggedIn(true);
      if (foundUser.userType === "admin") {
        setAccountCheck(true);
      }
      setUser(foundUser);
    } else {
      setIsLoggedIn(false);
    }
  };

  useEffect(() => {
    checkLoggedIn();
  }, []);

  return (
    
    <div className="card-container" title= "click the card to see the answer">
      
      {deletePopUp.show && (
        <DeletePopUp
          handleDeleteTrue={handleDeleteTrue}
          handleDeleteFalse={handleDeleteFalse}
        />
      )}

      {/* <div className="splitter"></div> */}
      <div onClick={handleReveal}>
        <div className="question-container">
          <h2>{questionAsked}</h2>
        </div>
        <div className="splitter"></div>

      <div onClick={handleReveal} className="flip-card">
        <div className="flip-card-inner">
        {answer ? (
          <div className="card-body" >
            <p>
              <span className="card-titles">League: </span>
              {league}
            </p>
            <p>
              <span className="card-titles">Football Team: </span>
              {footballTeam}
            </p>
            <p>
              <span className="card-titles">Asked By: </span>
              {askedBy}
            </p>
            <p>
              <span className="card-titles"> Team they support: </span>
              {teamUserWhoAskedSupports}
            </p>
            <p>
              <span className="card-titles">Keywords: </span>
              {keywords}
            </p>
          </div>
        ) : (
          <div className="card-body">
            <p>
              <span className="card-titles">Answer</span>
              <br />
              {questionAnswer}
            </p>
          </div>
        )}
        </div>
      </div>
  
      
      {accountCheck ? (
        <div className="button-div">
          <button type="submit" title="Edit Question" className="card-buttonOne" onClick={HandleEdit}>
            Edit
          </button>
          
          <button
            type="submit"
            className="card-buttonTwo"
            title="Remove Question"
            onClick={HandleDelete}
          >
            Delete
          </button>
          
          </div>
      ) : (
        <div></div>
      ) } 
    </div>
    </div>
  );
};

export default NewQCard;
