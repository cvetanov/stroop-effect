results = read.csv("results.csv")

maleResults = results[results$gender == "m", ]
femaleResults = results[results$gender == "f", ]

maleResults <- (maleResults$elapsedTimeIncongruent - maleResults$elapsedTimeCongruent) / 1000.0
femaleResults <- (femaleResults$elapsedTimeIncongruent - femaleResults$elapsedTimeCongruent) / 1000.0

t.test(maleResults, femaleResults)

boxplot(maleResults, femaleResults, ylab="difference in results", col=(c("blue","red")), names=c("male", "female"))
