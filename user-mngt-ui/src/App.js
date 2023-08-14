import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import Navbar from './components/Navbar';
import UserList from './components/UserList';

function App() {
  return (
    <>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path='/' element={<UserList/>}></Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
