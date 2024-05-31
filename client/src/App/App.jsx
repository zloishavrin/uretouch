import './App.css';
import axios from 'axios';
import { useEffect } from 'react';

function App() {

  useEffect(() => {
    axios.post('http://localhost/api/auth/login')
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.error(error.response.data.message);
      });
  }, []);

  return (
    <div className="App">
      <h1>Саня лох</h1>
    </div>
  );
}

export default App;
