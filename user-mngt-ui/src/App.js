import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import Navbar from './components/Navbar';
import UserList from './components/UserList';
import AddUser from './components/AddUser';

function App() {
  return (
    <>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path='/' element={<UserList/>}></Route>
          <Route path='/userList' element={<UserList/>}></Route>
          <Route path='/addUser' element={<AddUser/>}></Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
