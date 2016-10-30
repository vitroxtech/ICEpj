package squaring.vitrox.icepj.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

 public class MovieDetail implements Serializable {

     @JsonProperty("Title")
     private String Title;
     @JsonProperty("Year")
     private String Year;
     @JsonProperty("Rated")
     private String Rated;
     @JsonProperty("Released")
     private String Released;
     @JsonProperty("Runtime")
     private String Runtime;
     @JsonProperty("Genre")
     private String Genre;
     @JsonProperty("Director")
     private String Director;
     @JsonProperty("Writer")
     private String Writer;
     @JsonProperty("Actors")
     private String Actors;
     @JsonProperty("Plot")
     private String Plot;
     @JsonProperty("Language")
     private String Language;
     @JsonProperty("Country")
     private String Country;
     @JsonProperty("Awards")
     private String Awards;
     @JsonProperty("Poster")
     private String Poster;
     @JsonProperty("Metascore")
     private String Metascore;
     @JsonProperty("imdbRating")
     private String imdbRating;
     @JsonProperty("imdbVotes")
     private String imdbVotes;
     @JsonProperty("imdbID")
     private String imdbID;
     @JsonProperty("Type")
     private String Type;
     @JsonProperty("Response")
     private String Response;

     public String getCountry() {
         return Country;
     }

     public void setCountry(String country) {
         Country = country;
     }

     public String getAwards() {
         return Awards;
     }

     public void setAwards(String awards) {
         Awards = awards;
     }

     public String getPoster() {
         return Poster;
     }

     public void setPoster(String poster) {
         Poster = poster;
     }

     public String getImdbRating() {
         return imdbRating;
     }

     public void setImdbRating(String imdbRating) {
         this.imdbRating = imdbRating;
     }

     public String getImdbVotes() {
         return imdbVotes;
     }

     public void setImdbVotes(String imdbVotes) {
         this.imdbVotes = imdbVotes;
     }

     public String getImdbID() {
         return imdbID;
     }

     public void setImdbID(String imdbID) {
         this.imdbID = imdbID;
     }

     public String getType() {
         return Type;
     }

     public void setType(String type) {
         Type = type;
     }

     public String getResponse() {
         return Response;
     }

     public void setResponse(String response) {
         Response = response;
     }

     public String getTitle() {
         return Title;
     }

     public void setTitle(String title) {
         Title = title;
     }

     public String getYear() {
         return Year;
     }

     public void setYear(String year) {
         Year = year;
     }

     public String getRated() {
         return Rated;
     }

     public void setRated(String rated) {
         Rated = rated;
     }

     public String getReleased() {
         return Released;
     }

     public void setReleased(String released) {
         Released = released;
     }

     public String getRuntime() {
         return Runtime;
     }

     public void setRuntime(String runtime) {
         Runtime = runtime;
     }

     public String getGenre() {
         return Genre;
     }

     public void setGenre(String genre) {
         Genre = genre;
     }

     public String getDirector() {
         return Director;
     }

     public void setDirector(String director) {
         Director = director;
     }

     public String getWriter() {
         return Writer;
     }

     public void setWriter(String writer) {
         Writer = writer;
     }

     public String getActors() {
         return Actors;
     }

     public void setActors(String actors) {
         Actors = actors;
     }

     public String getPlot() {
         return Plot;
     }

     public void setPlot(String plot) {
         Plot = plot;
     }

     public String getLanguage() {
         return Language;
     }

     public void setLanguage(String language) {
         Language = language;
     }

     public String getMetascore() {
         return Metascore;
     }

     public void setMetascore(String metascore) {
         Metascore = metascore;
     }
 }
