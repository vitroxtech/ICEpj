package squaring.vitrox.icepj.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ApiResponse {

        @JsonProperty("Search")
        private List<Movie> Search;

        private String totalResults;

        @JsonProperty("Response")
        private String Response;


        public List<Movie> getSearch() {
            return Search;
        }

        public void setSearch(List<Movie> search) {
            Search = search;
        }

        public String getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(String totalResults) {
            this.totalResults = totalResults;
        }

        public String getResponse() {
            return Response;
        }

        public void setResponse(String response) {
            Response = response;
        }

}
