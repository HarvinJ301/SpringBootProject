import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle";
import "./App.css";
import { Route, Routes } from "react-router-dom";
import Questions from "./Components/Questions";

import AddQuestion from "./Components/AddQuestion";
import EditQuestion from "./Components/EditQuestion";
import Footer from "./Components/Footer";
import Login from "./Components/Login";

import WithNav from "./Components/WithNav";
import WithoutNav from "./Components/WithoutNav";


function App() {
  return (
    <div className="App">
      <Routes>
        <Route element={<WithoutNav/>}>
         <Route path="/" element={<Login />} />
        </Route>
        <Route element={<WithNav/>}>
         <Route path="/questions" element={<Questions />} />
         <Route path="/addquestion" element={<AddQuestion />} />
         <Route path="/editquestion" element={<EditQuestion />} />
        </Route>   
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
