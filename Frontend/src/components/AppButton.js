import Button from "@mui/material/Button";
import { styled } from '@mui/material/styles';
import { orange, deepOrange } from '@mui/material/colors';

// This component will override some of the default styles of the
// Material UI Button.
const AppButton = styled(Button)(({ theme }) => ({
  color: theme.palette.getContrastText(deepOrange["A400"]),
  backgroundColor: deepOrange["A400"],
  '&:hover': {
    color: theme.palette.getContrastText(orange[500]),
    backgroundColor: orange[500],
  },
  borderColor: orange[500],
  fontWeight: "bold"
}));

export default AppButton;