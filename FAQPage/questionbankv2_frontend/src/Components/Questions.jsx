import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { FaSearch } from "react-icons/fa";
import { BiRefresh } from "react-icons/bi";
import "../Styles/Questions.css";
import LoginPopUp from "./LoginPopUp";
import NewQCard from "./NewQCard";

const Questions = () => {
  const [api, setApi] = useState("http://localhost:8088/api/v1/question");
  const navigate = useNavigate();
  const [questions, setQuestions] = useState([]);
  const [leagueSearch, setLeagueSearch] = useState("");
  const [footballTeamSearch, setFootballTeamSearch] = useState("");
  const [keywordSearch, setKeyWordSearch] = useState("");
  const [askedBySearch, setAskedBySearch] = useState("");
  const [simpleSearchQuery, setSimpleSearchQuery] = useState("");
  const [answeredQuestionSearch, setAnsweredQuestionSearch] = useState("");
  const [timedPopup, setTimedPopup] = useState(false);

  const loadQuestions = () => {
    axios
      .get(api)
      .then((response) => setQuestions(response.data))
      .catch(console.log("Unable to retrieve data"));
  };

  //Advanced filter
  const filteredQuestions = questions.filter(
    (question) =>
      question.league.toLowerCase().includes(leagueSearch.toLowerCase()) &&
      question.footballTeam.toLowerCase().includes(footballTeamSearch.toLowerCase()) &&
      question.askedBy.toLowerCase().includes(askedBySearch.toLowerCase()) &&
      question.keywords
        .toLowerCase()
        .includes(keywordSearch.toLowerCase())
    &&
    question.answered
    .toLowerCase()
    .includes(answeredQuestionSearch.toLowerCase())
  );

  console.log(filteredQuestions);

  //An array of values stored from the API request which is used for the drop down menu
  const [allLeagues, setAllLeagues] = useState([]);
  const [allFootballTeams, setAllFootballTeams] = useState([]);
  const [allKeywords, setAllKeywords] = useState([]);

  //Methods to load the data for the drop down box
  const loadLeagues = () => {
    axios
      .get(api + "/League")
      .then((response) => setAllLeagues(response.data))
      .catch(console.log("Unable to retrieve data"));
  };

  const loadFootballTeams = () => {
    axios
      .get(api + "/FootballTeam")
      .then((response) => setAllFootballTeams(response.data))
      .catch(console.log("Unable to retrieve data"));
  };

  const loadKeywords = () => {
    axios
      .get(api + "/keyword")
      .then((response) => setAllKeywords(response.data))
      .catch(console.log("Unable to retrieve data"));
  };

  const handleRefresh = () => {
    setFootballTeamSearch("");
    setKeyWordSearch("");
    setLeagueSearch("");
    setAnsweredQuestionSearch("");
  };

  const handleSimpleSearch = (event) => {
    event.preventDefault();
    if (simpleSearchQuery === "") {
      setApi("http://localhost:8088/api/v1/question");
    } else {
      setApi(
        "http://localhost:8088/api/v1/question/search/" + simpleSearchQuery
      );
    }
    loadQuestions();
  };

  //when user clicks yes from pop up.
  const deleteQuestion = (id) => {
    axios.delete(api + "/" + id).then((response) => {
      console.log(response);
      loadQuestions();
    });
  };

  const editQuestion = (question) => {
    navigate("/editQuestion", { state: { q: question } });
  };

  useEffect(() => {
    loadLeagues();
    loadFootballTeams();
    loadKeywords();
    loadQuestions();

    //set the initial pop up to only occur first time
    const initialPopUp = sessionStorage.getItem("timedPopup");
    if (!initialPopUp) {
      const timer = setTimeout(() => {
        setTimedPopup(true);
        sessionStorage.setItem("timedPopup", "1");
      }, 100);
      return () => clearTimeout(timer);
    }
  }, []);

  return (
    <div>
      <h1>  Question Page</h1>
      <form id="filterDropdownForm">
        <div>
          <form onSubmit={handleSimpleSearch} className="searchBarForm">
            <input
              id="searchBar"
              type="text"
              placeholder="Search"
              name="search"
              title="Search any item within the Question Page"
              value={simpleSearchQuery}
              onChange={(event) => setSimpleSearchQuery(event.target.value)}
            />
            <button id="searchButton" type="submit" title="Search">
              <FaSearch />
            </button>
          </form>
        </div>
        <select
          className="filterDropdown"
          type="text"
          defaultValue=""
          name="Football Team"
          title="Filter questions by Football Team"
          onChange={(event) => setFootballTeamSearch(event.target.value)}
        >
          <option hidden value="">
            Football Team: {footballTeamSearch}
          </option>
          <option value="">Football Team.. </option>
          {allFootballTeams.map((footballTeam) => (
            <option key={footballTeam}>{footballTeam}</option>
          ))}
        </select>
        <select
          className="filterDropdown"
          type="text"
          defaultValue=""
          name="search"
          title="Filter questions by Keyword"
          onChange={(event) => setKeyWordSearch(event.target.value)}
        >
          <option hidden value="">
            Keywords: {keywordSearch}
          </option>
          <option value="">Keyword.. </option>
          {allKeywords.map((keyword) => (
            <option key={keyword}>{keyword}</option>
          ))}
        </select>
        <select
          className="filterDropdown"
          type="text"
          defaultValue=""
          name="League"
          title="Filter questions by League"
          onChange={(event) => setLeagueSearch(event.target.value)}
        >
          <option hidden value="">
            League: {leagueSearch}
          </option>
          <option value="">League.. </option>
          {allLeagues.map((league) => (
            <option key={league}>{league}</option>
          ))}
        </select>

        <button
          title="Refresh"
          className="refreshButton"
          type="submit"
          onClick={handleRefresh}
        >
          <BiRefresh />
        </button>

        <div className="form-group m-auto form-outline w-50">
          <label
            style={{ fontWeight: "bold" }}
            title="Filter all questions or those with just answers"
          >
            Filter Answered Questions:
          </label>
          <div title="Filter all questions or those with just answers">
            <label>
              <input
                type="radio"
                name="Answered Question"
                value=""
                onChange={(event) =>
                  setAnsweredQuestionSearch(event.target.value)
                }
                className="placeholderText mt-1"
              />
              <span>All Questions</span>
            </label>
          </div>

          <div title="Filter all questions or those with just answers">
            <label>
              <input
                type="radio"
                name="Answered Question"
                title="Filter all questions or those with just answers"
                value="true"
                onChange={(event) =>
                  setAnsweredQuestionSearch(event.target.value)
                }
                className="mt-1"
              />
              <span>Answered Questions</span>
            </label>
          </div>
        </div>
      </form>
      <div className="products-container">
        <div className="products-section">
          <div className="space-banner"></div>
          <div className="products-list">
            {filteredQuestions.map((question) => (
              <NewQCard
                key={question.questionID}
                question={question}
                delete={deleteQuestion}
                edit={editQuestion}
              />
            ))}
          </div>
          <LoginPopUp trigger={timedPopup} setTrigger={setTimedPopup}>
            <h3>Welcome to a Football question page</h3>
            <p>
              {" "}
              Here you can filter questions specifically to your needs, search
              for questions and even add your own question for users to answer/view!
            </p>
          </LoginPopUp>
          <div className="ad-banner"></div>
        </div>
      </div>
    </div>
  );
};
export default Questions;
