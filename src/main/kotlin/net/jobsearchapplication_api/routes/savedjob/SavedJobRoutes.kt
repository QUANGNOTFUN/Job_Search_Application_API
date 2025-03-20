package net.jobsearchapplication_api.routes.savedjob

fun Application.savedJobRoutes(repository: SavedJobRepository) {
    routing {
        authenticate {
            route("/saved-jobs") {
                get {
                    val userId = call.principal<UserIdPrincipal>()?.id
                    call.respond(repository.getSavedJobs(userId!!))
                }

                post("/{jobId}") {
                    val userId = call.principal<UserIdPrincipal>()?.id
                    val jobId = call.parameters["jobId"]?.let { UUID.fromString(it) }
                    if (userId != null && jobId != null) {
                        call.respond(repository.saveJob(userId, jobId))
                    }
                }

                delete("/{jobId}") {
                    val userId = call.principal<UserIdPrincipal>()?.id
                    val jobId = call.parameters["jobId"]?.let { UUID.fromString(it) }
                    if (userId != null && jobId != null) {
                        call.respond(repository.unsaveJob(userId, jobId))
                    }
                }
            }
        }
    }
}