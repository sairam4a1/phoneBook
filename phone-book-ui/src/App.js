import BasicTable from './components/BasicTable'
import { Button } from '@mui/material';
import './App.css';
import CreateContact from './components/CreateContact';
import { useState, useEffect } from 'react';
import SearchBar from './components/SearchBar';


function App() {
  const [open, setOpen] = useState(false);
  const [update, setUpdate] = useState(false);
  const [searchQuery, setSearchQuery] = useState("");
  const [contacts, setContacts] = useState([]);
  const [data, setData] = useState({});

  const getAllConatctsByPhoneNumber = () => {
    if (searchQuery.length === 10) {
      fetch("http://localhost:8081/contact/" + searchQuery).then((response) => {
        return response.json()
      }).then((res) => {
        setContacts(res?.data);
        console.log(contacts);
      }
      ).catch(error => {
        alert("Unable fetch the data")
      });
    }
  }
  const getAllConatcts = () => {
    fetch("http://localhost:8081/contact")
      .then(response => response.json())
      .then(res => {
        if (res.httpCode === 200) {
          setContacts(res?.data);
          console.log(res);
        } else {
          alert(res?.message);
        }
      })
      .catch(error => {
        // Handle any errors
        console.error('Error:', error);
      });
  }
  useEffect(() => {

  }, []);
  useEffect(() => {
    if (searchQuery.trim() === '') {
      getAllConatcts();
    } else {
      getAllConatctsByPhoneNumber();
    }
  }, [searchQuery]);
  const handleDialogueOpen = (flag) => {
    setUpdate(false);
    setOpen(flag)
  };
  const handleUpadteMode = () => {
    setUpdate(true);
  };
  const handleAddMode = () => {
    setUpdate(false);
    handleDialogueOpen(true);
  };
  const addContactToContactList = (contact) => {
    contacts[contacts.length] = contact;
  }

  return (
    <div className="App">
      <div>
        <h4>Contact Manger</h4>
      </div>
      <div>
        <SearchBar setContacts={setContacts} setSearchQuery={setSearchQuery} />
      </div>
      <div>
        <Button color='primary' onClick={handleAddMode}>Add Contact</Button>
      </div>
      <div className='grid'>
        <BasicTable contacts={contacts} handleUpadteMode={handleUpadteMode}
          handleDialogueOpen={handleDialogueOpen} setData={setData} setContacts ={setContacts}></BasicTable>
      </div>
      <CreateContact isopen={open} handleDialogueOpen={handleDialogueOpen}
        isUpdate={update} data={update?data:{}} addContactToContactList={addContactToContactList} ></CreateContact>
    </div>
  );
}

export default App;
