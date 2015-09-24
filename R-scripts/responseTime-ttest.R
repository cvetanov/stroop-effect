results = read.csv('results.csv')
# remove all results which have high error percentage
results <- results[results$errorCongruent < 0.5, ]
results <- results[results$errorIncongruent < 0.5, ]

responseTimeCongruent = results$elapsedTimeCongruent
responseTimeIncongruent = results$elapsedTimeIncongruent

responseTimeCongruentSeconds = responseTimeCongruent / 1000.0
responseTimeIncongruentSeconds = responseTimeIncongruent / 1000.0

# output is p-value
# if p-value is less or equal to confidence level(0.01), then the difference in the results is statistically significant
t.test(responseTimeCongruentSeconds, responseTimeIncongruentSeconds, conf.level = 0.99)

# different ways of displaying the results
#1
boxplot(responseTimeCongruentSeconds, responseTimeIncongruentSeconds, ylab="response time", col=(c("gold","darkgreen")), names=c("congruent", "incongruent"))

#2
densityCongruent = density(responseTimeCongruentSeconds)
densityIncongruent = density(responseTimeIncongruentSeconds)
plot(densityCongruent, xlim = c(0, 35), xlab = "response time in seconds", main = "Combined plots")
polygon(densityCongruent, col = "red")
polygon(densityIncongruent, col="blue")

