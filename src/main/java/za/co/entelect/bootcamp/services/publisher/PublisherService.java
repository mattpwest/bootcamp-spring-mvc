package za.co.entelect.bootcamp.services.publisher;

import za.co.entelect.bootcamp.domain.Gender;
import za.co.entelect.bootcamp.domain.Publisher;
import za.co.entelect.bootcamp.domain.Superhero;
import za.co.entelect.bootcamp.services.generic.ReadService;
import za.co.entelect.bootcamp.services.generic.WriteService;

public interface PublisherService extends ReadService<Publisher, Integer>, WriteService<Publisher, Integer> {

    void registerSuperhero(Publisher publisher, Superhero superhero);
    void unregisterSuperhero(Superhero hero);

    Publisher getPublisherBySuperhero(Superhero superhero);

    Iterable<Superhero> getSuperheroesByPublisher(Publisher publisher);

    SuperheroSummaryReport generateHeroSummaryReport(Publisher publisher);

    Iterable<Superhero> getSuperheroesByPublisherAndGender(Publisher publisher, Gender gender);

    class SuperheroSummaryReport {
        private Publisher publisher;
        private int count = 0;
        private int female = 0;
        private int male = 0;
        private int other = 0;

        public SuperheroSummaryReport() {}

        public Publisher getPublisher() {
            return publisher;
        }

        public void setPublisher(Publisher publisher) {
            this.publisher = publisher;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getFemale() {
            return female;
        }

        public void setFemale(int female) {
            this.female = female;
        }

        public int getMale() {
            return male;
        }

        public void setMale(int male) {
            this.male = male;
        }

        public int getOther() {
            return other;
        }

        public void setOther(int other) {
            this.other = other;
        }
    }
}
