import * as React from 'react';
import axios from "axios"
import { useParams } from "react-router-dom";

// import AppBar from '@mui/material/AppBar';
// import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import Stack from '@mui/material/Stack'
import Box from '@mui/material/Box'

import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';

import StarIcon from '@mui/icons-material/Star'
import StarHalfIcon from '@mui/icons-material/StarHalf'

import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
// import AddIcon from '@mui/icons-material/Add'
import MusicNoteIcon from '@mui/icons-material/MusicNote';
import SearchIcon from '@mui/icons-material/Search';
import PlayArrowIcon from '@mui/icons-material/PlayArrow';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import GroupIcon from '@mui/icons-material/Group';

import Page from '../../components/Page';
import PageHeader from '../../components/PageHeader';
import PageBody from '../../components/PageBody';
import PageFooter from '../../components/PageFooter';
import PageHeaderTitle from '../../components/PageHeaderTitle';
import PageHeaderSubtitle from '../../components/PageHeaderSubtitle';
import PageHeaderIcon from '../../components/PageHeaderIcon';
import AppBar from '../../components/Appbar';
import SideBar from '../../components/Sidebar';
import { Input } from '@mui/material';

const Me = () => {
    //USE_PARAMS
    let { trackId } = useParams();

    // USE_STATES
    const [data, setData] = React.useState({})
    const [error, setError] = React.useState("")

    // const [modalOpen, setModalOpen] = React.useState(false);
    // const [modalData, setModalData] = React.useState({});

    // HANDLERS
    const handleGetUser = async () => {
        try {
            let token = localStorage.getItem("token")
            const url = "http://localhost:8081/api/users/self"
            const { data: res } = await axios.get(url, {
                headers: {
                  Authorization: `Bearer ${token}`
                }
            })
            console.log(res);
            setData(res)
        } catch (error) {
            if (
                error.response &&
                error.response.status >= 400 &&
                error.response.status <= 500
            ) {
                setError(error.response.data.message)
            }
        }
    }

    const handleLogout = () => {
        localStorage.removeItem("token")
        window.location.reload()
        window.location.replace("/")
    }

    // USE_EFFECT
    React.useEffect(() => {
        handleGetUser()
      }, []);

    // DOM
    return (
        <div className="Tracks">
            <AppBar>
                <Toolbar>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        Searchify!
                    </Typography>
                    <Button color="inherit" onClick={handleLogout}>Logout</Button>
                </Toolbar>
            </AppBar>
            <SideBar>
                <Stack direction="column" alignItems="center" spacing={1}>
                    <IconButton aria-label="add" size="large" href={'/users/self'}>
                        <AccountCircleIcon />
                    </IconButton>
                    <IconButton aria-label="add" size="large" href={'/'}>
                        <MusicNoteIcon />
                    </IconButton>
                    <IconButton aria-label="add" size="large" href={'/users/self/favorites'}>
                        <StarIcon />
                    </IconButton>
                    <IconButton aria-label="add" size="large" href={'/users'}>
                        <GroupIcon />
                    </IconButton>
                </Stack>
            </SideBar>
            <Page>
                <PageHeader
                    icon= {
                        <PageHeaderIcon>
                            <Stack direction="row" alignItems="center" spacing={1}>
                                <AccountCircleIcon size="large"/>
                            </Stack>
                        </PageHeaderIcon>
                    }
                    title = {
                        <PageHeaderTitle title={data.firstname}></PageHeaderTitle>
                    }
                    subtitle = {
                        <PageHeaderSubtitle subtitle={data.lastname}></PageHeaderSubtitle>
                    }
                    />
                <PageBody>
                    <TableContainer component={Paper}>
                        <Table sx={{ width: 1 }} aria-label="simple table">
                            <TableHead>
                                <TableRow>
                                    <TableCell>Username</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                <TableRow>
                                    <TableCell>{data.username}</TableCell>
                                </TableRow>
                            </TableBody>
                        </Table>
                    </TableContainer>
                </PageBody>
                <PageFooter></PageFooter>
            </Page>
        </div>
    )
}
export default Me