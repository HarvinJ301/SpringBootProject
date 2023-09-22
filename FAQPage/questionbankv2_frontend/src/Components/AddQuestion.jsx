import { useNavigate } from "react-router-dom";
import { useState } from "react";
import axios from "axios";
import "../Styles/AddQuestion.css";

const AddQuestion = () => {
  const api = "http://localhost:8088/api/v1/question";
  const navigate = useNavigate();

  // Validation constants, make sure these fields are not blank when submitted
  const [League, setLeague] = useState("");
  const [footballTeam, setfootballTeam] = useState("");
  const [askedBy, setaskedBy] = useState("");
  const [teamUserWhoAskedSupports, setteamUserWhoAskedSupports] = useState("");
  const [questionAsked, setquestionAsked] = useState("");

  const [keywords, setkeywords] = useState("");
  const [answered, setAnswered] = useState("");

  // Validation constants, make sure these fields are not blank when submitted
  const [noLeague, setNoLeague] = useState(false);
  const [nofootballTeam, setNofootballTeam] = useState(false);
  const [noaskedBy, setNoaskedBy] = useState(false);
  const [noteamUserWhoAskedSupports, setNoteamUserWhoAskedSupports] = useState(false);
  const [noquestionAsked, setNoquestionAsked] = useState(false);

  // Validation constrants, makes sure fields are filled more than the minimum number of characters
  const [shortLengthfootballTeam, setShortLengthfootballTeam] = useState(false);
  const [shortLengthaskedBy, setShortLengthaskedBy] =
    useState(false);
  const [shortLengthteamUserWhoAskedSupports, setShortLengthteamUserWhoAskedSupports] =
    useState(false);
  const [shortLengthquestionAsked, setShortLengthquestionAsked] =
    useState(false);

  // Validation constrants, makes sure fields are filled more than the maximum number of characters
  const [longLengthfootballTeam, setLongLengthfootballTeam] = useState(false);
  const [longLengthaskedBy, setLongLengthaskedBy] =
    useState(false);
  const [longLengthteamUserWhoAskedSupports, setLongLengthteamUserWhoAskedSupports] =
    useState(false);
  const [longLengthquestionAsked, setLongLengthquestionAsked] =
    useState(false);
  const [longLengthKeywords, setLongLengthKeywords] =
    useState(false);

  // Function and conditionals to validate the form for attributes that are mandatory
  const handleQuestionForm = (event) => {
    event.preventDefault();

    // If false then there is a value for the field
    setNoLeague(false);
    setNofootballTeam(false);
    setNoaskedBy(false);
    setNoteamUserWhoAskedSupports(false);
    setNoquestionAsked(false);

    setShortLengthfootballTeam(false);
    setShortLengthaskedBy(false);
    setShortLengthteamUserWhoAskedSupports(false);
    setShortLengthquestionAsked(false);

    setLongLengthfootballTeam(false);
    setLongLengthaskedBy(false);
    setLongLengthteamUserWhoAskedSupports(false);
    setLongLengthquestionAsked(false);
    setLongLengthKeywords(false);

    // If the field is blank then noField becomes true and the axios.post won't work
    if (
      League === "" &&
      footballTeam === "" &&
      askedBy === "" &&
      teamUserWhoAskedSupports === "" &&
      questionAsked === "" &&
      footballTeam.length < 2 &&
      footballTeam.length > 30 &&
      askedBy.length < 2 &&
      askedBy.length > 50 &&
      teamUserWhoAskedSupports.length < 2 &&
      teamUserWhoAskedSupports.length > 30 &&
      questionAsked.length < 2 &&
      questionAsked.length > 255 &&
      keywords.length > 50
    ) {
      setNoLeague(true);
      setNofootballTeam(true);
      setNoaskedBy(true);
      setNoteamUserWhoAskedSupports(true);
      setNoquestionAsked(true);

      setShortLengthfootballTeam(true);
      setLongLengthfootballTeam(true);
      setShortLengthaskedBy(true);
      setLongLengthaskedBy(true);
      setShortLengthteamUserWhoAskedSupports(true);
      setLongLengthteamUserWhoAskedSupports(true);
      setShortLengthquestionAsked(true);
      setLongLengthquestionAsked(true);
      setLongLengthKeywords(true);
    } else if (League === "") {
      setNoLeague(true);
    } else if (footballTeam === "") {
      setNofootballTeam(true);
    } else if (askedBy === "") {
      setNoaskedBy(true);
    } else if (teamUserWhoAskedSupports === "") {
      setNoteamUserWhoAskedSupports(true);
    } else if (questionAsked === "") {
      setNoquestionAsked(true);
    } else if (footballTeam.length < 2) {
      setShortLengthfootballTeam(true);
    } else if (footballTeam.length > 30) {
      setLongLengthfootballTeam(true);
    } else if (askedBy.length < 2) {
      setShortLengthaskedBy(true);
    } else if (askedBy.length > 50) {
      setLongLengthaskedBy(true);
    } else if (teamUserWhoAskedSupports.length < 2) {
      setShortLengthteamUserWhoAskedSupports(true);
    } else if (teamUserWhoAskedSupports.length > 30) {
      setLongLengthteamUserWhoAskedSupports(true);
    } else if (questionAsked.length < 2) {
      setShortLengthquestionAsked(true);
    } else if (questionAsked.length > 255) {
      setLongLengthquestionAsked(true);
    } else if (keywords.length > 50) {
      setLongLengthKeywords(true);
    } else {
      axios
        .post(api, {
          League: League,
          footballTeam: footballTeam,
          askedBy: askedBy,
          teamUserWhoAskedSupports: teamUserWhoAskedSupports,
          questionAsked: questionAsked,
          keywords: keywords,
          answered: answered,
        })
        .then(navigate("/questions"))
        .catch((error) => {
          console.log(error);
        });
    }
  };

  return (
    <div className="auth-form-container">
      <form className="auth-form" onSubmit={handleQuestionForm}>
        <div className="auth-form-content">
          <h2 className="auth-form-title">Question Form</h2>
          <div>
            <h7>
              Questions marked <span style={{ color: "red" }}>*</span> are
              required
            </h7>
          </div>
          <br />
          <div className="form-group m-auto form-outline w-50 align-center">
            <label style={{ fontWeight: "bold" }}>
              The League the team is in <span style={{ color: "red" }}>*</span>
            </label>
            <select
              type="text"
              defaultValue=""
              margin="0 auto"
              onChange={(event) => setLeague(event.target.value)}
              className="placeholderText form-control mt-1 selectpicker show-tick"
            >
              <option hidden value="">
                Choose League:
              </option>
              <option>Premier League</option>
              <option>Championship</option>
              <option>League 1</option>
              <option>League 2</option>
              <option>LaLiga</option>
              <option>Ligue 1</option>
              <option>Serie A</option>
              <option>Bundesliga</option>
              
            </select>

            {/* if the noField is true then an error message displays to the user that field cannot be blank */}
            {noLeague && (
              <span className="error text-danger text-decoration-underline">
                League cannot be blank
              </span>
            )}

            <br />
          </div>

          <div
            style={{ fontWeight: "bold" }}
            className="form-group m-auto form-outline w-50"
          >
            <label>
              The football team the question is about <span style={{ color: "red" }}>*</span>
            </label>
            <input
              type="text"
              placeholder="footballTeam (e.g. Arsenal)"
              value={footballTeam}
              onChange={(event) => setfootballTeam(event.target.value)}
              className="placeholderText form-control mt-1"
            />
          </div>
          {nofootballTeam && (
            <span className="error text-danger text-decoration-underline">
              Football team cannot be blank
            </span>
          )}

          {shortLengthfootballTeam && (
            <span className="error text-danger text-decoration-underline">
              Football Team character length cannot be less than 2
            </span>
          )}

          {longLengthfootballTeam && (
            <span className="error text-danger text-decoration-underline">
              Football Team character length cannot be more than 30
            </span>
          )}

          <br />

          <div
            style={{ fontWeight: "bold" }}
            className="form-group m-auto form-outline w-50"
          >
            <label>
              Please input your name/alias{" "}
              <span style={{ color: "red" }}>*</span>
            </label>
            <input
              type="text"
              placeholder="Asked By"
              value={askedBy}
              onChange={(event) => setaskedBy(event.target.value)}
              className="placeholderText form-control mt-1"
            />
          </div>
          {noaskedBy && (
            <span className="error text-danger text-decoration-underline">
              Name cannot be blank
            </span>
          )}

          {shortLengthaskedBy && (
            <span className="error text-danger text-decoration-underline">
              Name character length cannot be less than 2
            </span>
          )}

          {longLengthaskedBy && (
            <span className="error text-danger text-decoration-underline">
              Name character length cannot be or more than 50
            </span>
          )}

          <br />

          <div
            style={{ fontWeight: "bold" }}
            className="form-group m-auto form-outline w-50"
          >
            <label>
              Who do you support? <span style={{ color: "red" }}>*</span>
            </label>
            <input
              type="text"
              placeholder="Who do you support? (e.g. Arsenal)"
              value={teamUserWhoAskedSupports}
              onChange={(event) => setteamUserWhoAskedSupports(event.target.value)}
              className="placeholderText form-control mt-1"
            />
          </div>
          {noteamUserWhoAskedSupports && (
            <span className="error text-danger text-decoration-underline">
              Team you suport cannot be blank
            </span>
          )}

          {shortLengthteamUserWhoAskedSupports && (
            <span className="error text-danger text-decoration-underline">
             Team you support character length cannot be less than 2
            </span>
          )}

          {longLengthteamUserWhoAskedSupports && (
            <span className="error text-danger text-decoration-underline">
              Team you support character length cannot be more than 30
            </span>
          )}

          <br />

          <div
            style={{ fontWeight: "bold" }}
            className="form-group m-auto form-outline w-50"
          >
            <label>
              Question?{" "}
              <span style={{ color: "red" }}>*</span>
            </label>
            <input
              type="text"
              placeholder="Enter Question"
              value={questionAsked}
              onChange={(event) => setquestionAsked(event.target.value)}
              className="placeholderText form-control mt-1"
            />
          </div>
          {noquestionAsked && (
            <span className="error text-danger text-decoration-underline">
              Question cannot be blank
            </span>
          )}

          {shortLengthquestionAsked && (
            <span className="error text-danger text-decoration-underline">
              Question character length cannot be less than 2
            </span>
          )}

          {longLengthquestionAsked && (
            <span className="error text-danger text-decoration-underline">
              Question character length cannot be more than 255
            </span>
          )}
          <br />

          <div
            style={{ fontWeight: "bold" }}
            className="form-group m-auto form-outline w-50 col-lg-3 center-block"
          >
            <label>Keywords</label>
            <input
              type="text"
              placeholder="Keyword Related to the Question (e.g. Premier League)"
              defaultValue="-No Keyword"
              value={keywords}
              onChange={(event) => setkeywords(event.target.value)}
              className="placeholderText form-control mt-1"
            />
          </div>

          {longLengthKeywords && (
            <span className="error text-danger text-decoration-underline">
              Keyword character length cannot be more than 50
            </span>
          )}
          <br />

          <div>
            <label>
              <input
                type="hidden"
                value="false"
                onChange={(event) => setAnswered(event.target.value)}
              />
            </label>
          </div>
          {/* <br /> */}

          {/* On submit, if the mandatory fields are filled a pop up will tell you that your question is being vetted, and the user is navigated back to the home page. */}
          <button
            type="submit"
            className="submit-button"
            title="Add the question to the Page"
            onClick={() => {
              if (
                League === "" &&
                footballTeam === "" &&
                askedBy === "" &&
                teamUserWhoAskedSupports === "" &&
                questionAsked === "" &&
                footballTeam.length < 2 &&
                footballTeam.length > 30 &&
                askedBy.length < 2 &&
                askedBy.length > 50 &&
                teamUserWhoAskedSupports.length < 2 &&
                teamUserWhoAskedSupports.length > 30 &&
                questionAsked.length < 2 &&
                questionAsked.length > 255 &&
                keywords.length > 50
              ) {
                setNoLeague(true);
                setNofootballTeam(true);
                setNoaskedBy(true);
                setNoteamUserWhoAskedSupports(true);
                setNoquestionAsked(true);
                setShortLengthfootballTeam(true);
                setLongLengthfootballTeam(true);
                setShortLengthaskedBy(true);
                setLongLengthaskedBy(true);
                setShortLengthteamUserWhoAskedSupports(true);
                setLongLengthteamUserWhoAskedSupports(true);
                setShortLengthquestionAsked(true);
                setLongLengthquestionAsked(true);
                setLongLengthKeywords(true);
              } else if (League === "") {
                setNoLeague(true);
              } else if (footballTeam === "") {
                setNofootballTeam(true);
              } else if (askedBy === "") {
                setNoaskedBy(true);
              } else if (teamUserWhoAskedSupports === "") {
                setNoteamUserWhoAskedSupports(true);
              } else if (questionAsked === "") {
                setNoquestionAsked(true);
              } else if (footballTeam.length < 2) {
                setShortLengthfootballTeam(true);
              } else if (footballTeam.length > 30) {
                setLongLengthfootballTeam(true);
              } else if (askedBy.length < 2) {
                setShortLengthaskedBy(true);
              } else if (askedBy.length > 50) {
                setLongLengthaskedBy(true);
              } else if (teamUserWhoAskedSupports.length < 2) {
                setShortLengthteamUserWhoAskedSupports(true);
              } else if (teamUserWhoAskedSupports.length > 30) {
                setLongLengthteamUserWhoAskedSupports(true);
              } else if (questionAsked.length < 2) {
                setShortLengthquestionAsked(true);
              } else if (questionAsked.length > 255) {
                setLongLengthquestionAsked(true);
              } else if (keywords.length > 50) {
                setLongLengthKeywords(true);
              } else {
                alert("Your question is now successfully created");
              }
            }}
          >
            Submit Question
          </button>
        </div>
        <br />
      </form>
      <br />
      <br />
      <br />
      <br />
      <br />
    </div>
  );
};
export default AddQuestion;
