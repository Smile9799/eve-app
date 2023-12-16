import logo from './logo.svg';
import './App.css';
import { Routes, Route } from 'react-router-dom';
import Layout from './component/Layout';
import Login from './component/Login';
import Register from './component/Register';
import RequireAuth from './component/RequireAuth';
import Budget from './component/Budgets';
import Missing from './component/Missing';

const ROLES = {
  'DEFAULT' : 'DEFAULT_AUTHORITY'
}

function App() {
  return (
    <Routes>
      <Route path='/' element={<Layout/>}>
        {/* Public routes */}
        <Route index element={<Login/>}/>
        <Route path='login' element={<Login/>}/>
        <Route path='register' element={<Register/>}/>

        {/* Protected routes */}
        <Route element={<RequireAuth allowedRoles={[ROLES.DEFAULT]}/>}>
          <Route path='budget' element={<Budget/>}/>
        </Route>

        {/* catch all */}
        <Route path="*" element={<Missing />} />
      </Route>
    </Routes>
  );
}

export default App;
