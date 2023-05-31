import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import { useState, useEffect } from 'react';


export default function CreateContact({ isopen, handleDialogueOpen, isUpdate, data, addContactToContactList }) {
    const [contact, setContact] = useState({});
    useEffect(() => {
        if(isUpdate){
            setContact(data);
        }else{
            setContact({});
        }
    }, [data])

    const handleClose = () => {
        handleDialogueOpen(false);
        setContact({});
    };

    const handleChange = (event) => {
        const value = event.target.value;
        setContact({ ...contact, [event.target.name]: value });
        data[event.target.name] = value
    }
    const handleRequest = () => {
        fetch("http://localhost:8081/contact", options)
            .then(response => response.json())
            .then(res => {
                if (res.httpCode === 201) {
                    addContactToContactList(res?.data);
                    handleClose();
                }
                if (res.httpCode === 200) {
                    handleClose();
                }
                data = {};
                alert(res?.message);
            })
            .catch(error => {
                // Handle any errors
                console.error('Error:', error);
            });
    };
    const options = {
        method: isUpdate ? 'PUT' : 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(contact)
    };

    return (
        <div>
            <Dialog open={isopen} onClose={handleClose}>
                <DialogTitle>{!isUpdate ? "Create" : "Update"} Contact</DialogTitle>
                <DialogContent>
                    <TextField autoFocus margin="dense" id="first-name"
                        label="First Name" fullWidth variant="standard" required
                        name="firstName"
                        value={data?.firstName} onChange={handleChange} />
                    <TextField id="last-name"
                        label="Last Name" margin="dense" required variant="standard"
                        name="lastName" value={data?.lastName}
                        fullWidth
                        onChange={handleChange} />
                    <TextField id="phone-number" type="number"
                        label="Phone Number" margin="dense" required variant="standard"
                        name="phoneNumber" value={data?.phoneNumber}
                        fullWidth
                        onChange={handleChange} />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    {!isUpdate && <Button onClick={handleRequest}>Create</Button>}
                    {isUpdate && <Button onClick={handleRequest}>Update</Button>}
                </DialogActions>
            </Dialog>
        </div>);
}