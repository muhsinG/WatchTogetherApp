import Button from "./Button";
import MovieTable from "./MovieTable";

const Dashboard = () => {
   return (
          <div className="grid-container-element">
             <div className="grid-child-element purple">
                <MovieTable />
             </div>
          </div>
   )
}

export default Dashboard