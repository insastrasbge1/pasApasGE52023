/*
Copyright 2000- Francois de Bertrand de Beuvron

This file is part of CoursBeuvron.

CoursBeuvron is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

CoursBeuvron is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with CoursBeuvron.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.insa.beuvron.cours.multiTache.exemplesCours.trieurs;

import java.time.Duration;

/**
 *
 * @author francois
 */
public class FinalResult {
    
    private FinalStatus status;
    private long elapsedTime;

    public FinalResult(FinalStatus status, long elapsedTime) {
        this.status = status;
        this.elapsedTime = elapsedTime;
    }

    public FinalStatus getStatus() {
        return status;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public static String timeToString(long duree) {
        Duration d = Duration.ofMillis(duree);
        return d.toString().substring(2);
    }

    @Override
    public String toString() {
        return "FinalResult{" + "status=" + status + ", elapsedTime=" + timeToString(elapsedTime) + '}';
    }
    
    public String timeOrStatus() {
        if (this.status != FinalStatus.OK) {
            return this.status.toString();
        } else {
            return timeToString(this.elapsedTime);
        }
    }
    
    
    
}
