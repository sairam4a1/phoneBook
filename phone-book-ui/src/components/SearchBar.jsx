import TextField from "@mui/material/TextField";

const SearchBar = ({ setSearchQuery}) => (
    <div className="search-box">
        <TextField
            id="search-bar"
            className="text"
            type="search" onClick={(e) => {
                if (e.target.value.length === 0) {
                    setSearchQuery("");
                }
            }}
            onChange={(e) => {
                e.preventDefault();
                if (e.target.value.length >= 10) {
                    setSearchQuery(e.target.value);
                } else {
                    setSearchQuery("");
                }
            }}
            label="Enter Phone Number"
            variant="outlined"
            placeholder="Search..."
            size="small"
        />
    </div>
);
export default SearchBar;
