import logo from './logo.svg';
import './App.css';
import Header from './components/Header'
import Dashboard from "./components/Dashboard";
import Button from "./components/Button";

function App() {
  return (
    <div className="App">
      {/*<Header />*/}
      {/*  <div className="parent">*/}
      {/*  <form>*/}
      {/*    <div className="input-container">*/}
      {/*    <label>*/}
      {/*      Username:*/}
      {/*      <input type="text" name="username"/>*/}
      {/*    </label>*/}
      {/*    </div>*/}
      {/*    <div className="input-container">*/}
      {/*    <label>*/}
      {/*      Password:*/}
      {/*      <input type="text" name="password"/>*/}
      {/*    </label>*/}
      {/*    </div>*/}
      {/*    <input type="submit" value="Submit"/>*/}
      {/*      <button type="button"> Change Password</button>*/}
      {/*  </form>*/}
      {/*  </div>*/}

      <Header />
      <Dashboard />

    </div>
  );
}

export default App;
