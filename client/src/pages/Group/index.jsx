import * as React from 'react';
import { Link } from "react-router-dom"
import axios from "axios"


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
import AddIcon from '@mui/icons-material/Add'
import MusicNoteIcon from '@mui/icons-material/MusicNote';
import VisibilityIcon from '@mui/icons-material/Visibility';
import SearchIcon from '@mui/icons-material/Search';
import PlayArrowIcon from '@mui/icons-material/PlayArrow';
import OpenInNewIcon from '@mui/icons-material/OpenInNew';
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

import NoToDi from '../../components/NoToDi';

import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { height } from '@mui/system';

const Group = () => {
    // USE_STATE
    const [data, setData] = React.useState([])
    const [error, setError] = React.useState("")

    // HANDLERS
    const handleGetUsersWithTracks = async () => {
        try {
            let token = localStorage.getItem("token")
            const url = "http://localhost:8081/api/users"
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
        handleGetUsersWithTracks()
      }, []);

      console.log(data);

    // DOM
    return (
        <div className="Tracks">
            <AppBar>
                <Toolbar>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        Searchify
                    </Typography>
                    {/* <Button color="inherit" href={'/'}>Tracks</Button>
                    <Button color="inherit" href={'/favorites'}>Favorites</Button> */}
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
                                <GroupIcon size="large"/>
                            </Stack>
                        </PageHeaderIcon>
                    }
                    title = {
                        <PageHeaderTitle title={"Users"}></PageHeaderTitle>
                    }
                    subtitle = {
                        <PageHeaderSubtitle subtitle={"And Favorites Tracks"}></PageHeaderSubtitle>
                    }/>
                <PageBody>
                    {/* {data.length != 0 ? 
                        data.map((user) => (
                        <div>
                            <TableContainer component={Paper}>
                            <Table sx={{ width: 1 }} aria-label="simple table">
                                <TableHead>
                                <TableRow>
                                        <TableCell>{user.firstname}</TableCell>
                                        <TableCell>{user.lastname}</TableCell>
                                        <TableCell></TableCell>
                                        <TableCell></TableCell>
                                        <TableCell></TableCell>
                                    </TableRow>
                                    <TableRow>
                                        <TableCell>Name</TableCell>
                                        <TableCell>Artists</TableCell>
                                        <TableCell>Popularity</TableCell>
                                        <TableCell>Duration (ms)</TableCell>
                                        <TableCell>Actions</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {user.favorites.map((favorite) => (
                                    <TableRow
                                    key={favorite.track._id}
                                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                                        <TableCell component="th" scope="row"><b>{favorite.track.name}</b></TableCell>
                                        <TableCell>{favorite.track.artists}</TableCell>
                                        <TableCell>{favorite.track.popularity}</TableCell>
                                        <TableCell>{favorite.track.durationMs}</TableCell>
                                        <TableCell align="right">
                                            <Stack direction="row" alignItems="center" spacing={1}>
                                                <Link to={'/tracks/' + favorite.track._id}>
                                                    <IconButton aria-label="star" size="small">
                                                        <OpenInNewIcon />
                                                    </IconButton>
                                                </Link>
                                                <IconButton aria-label="star" size="small" href={favorite.track.previewUrl}>
                                                    <PlayArrowIcon />
                                                </IconButton>
                                                <IconButton aria-label="star" size="small">
                                                    <StarIcon />
                                                </IconButton>         
                                            </Stack>
                                        </TableCell>
                                    </TableRow>
                                    ))}
                                </TableBody>
                            </Table>
                        </TableContainer>
                        <div style={{"padding": "30px"}}></div>
                    </div>
                    ))
                    : <NoToDi show={true}/>} */}
                    {data.length != 0 ? 
                        <TableContainer component={Paper}>
                        <Table sx={{ width: 1 }} aria-label="simple table">
                            <TableHead>
                                <TableRow>
                                    <TableCell>User name</TableCell>
                                    <TableCell>First name</TableCell>
                                    <TableCell>Last name</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                            {data.map((row) => (
                                <TableRow
                                key={row.id}
                                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
                                    <TableCell component="th" scope="row"><b>{row.username}</b></TableCell>
                                    <TableCell>{row.firstname}</TableCell>
                                    <TableCell>{row.lastname}</TableCell>
                                </TableRow>
                            ))}
                            </TableBody>
                        </Table>
                    </TableContainer> : <NoToDi show={true}/>}
                </PageBody>
                <PageFooter></PageFooter>
            </Page>

            <ToastContainer
                position="top-center"
                autoClose={5000}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
                />
        </div>
    )
}
export default Group