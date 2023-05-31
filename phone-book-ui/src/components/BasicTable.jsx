import { useState, Fragment } from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { Delete, EditRounded } from '@mui/icons-material';
import { AddIcCall } from '@mui/icons-material';
import '../App.css';
export default function BasicTable({ contacts, handleUpadteMode, handleDialogueOpen, setData, setContacts }) {
    const [id, setId] = useState("");
    const handleChange = (event) => {
        handleDialogueOpen(true);
        handleUpadteMode();
    }
    const handleItemClick = item => {
        setId(item?.id)
        setData(item)
    }

    const handleDelete = () => {
        fetch("http://localhost:8081/contact/" + id, deleteOptions)
            .then(response => response.json())
            .then(res => {
                alert(res?.message);
                setContacts(contacts.filter(contact => contact.id !== id))
            })
            .catch(error => {
                // Handle any errors
                console.error('Error:', error);
            });
    }
    const deleteOptions = {
        method: "DELETE",
        headers: {
            'Content-Type': 'application/json'
        }
    };
    return (
        <div>
            <TableContainer component={Paper}>
                <Table sx={{ maxWidth: 300 }} aria-label="simple table">
                    <TableBody>
                        {contacts.map((contact, index) => (
                            <TableRow selected={true} hover={true} onClick={() => handleItemClick(contact)}
                                key={contact?.id}
                                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                                <TableCell align="center">
                                    <span >
                                        {contact.firstName + " " + contact.lastName}
                                    </span>
                                    <br></br>
                                    <AddIcCall></AddIcCall>
                                    <span>
                                        {contact.phoneNumber.slice(0,3)}-{contact.phoneNumber.slice(3,6)}-{contact.phoneNumber.slice(6)}
                                    </span>
                                </TableCell>
                                <TableCell align="center">
                                    <Fragment>
                                        <EditRounded onClick={handleChange} color="danger" />
                                        <Delete onClick={handleDelete} color="primary" />
                                    </Fragment>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
}