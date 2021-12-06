const Logger = (logLevel: LogSeverity) => {
    
    return {
        Log: function (type: LogType, severity: LogSeverity, message: string) {
            const sevInfo = GetSevInfo(severity)
            const today = new Date()
            if (severity >= logLevel) console.log(`%c[${today.getSeconds()}.${today.getMilliseconds()}][${type}][${[sevInfo.print]}] ${message}`,sevInfo.color);
        }
    }
}

export enum LogType {
    API = "API",
    SET = "SET",
}

export enum LogSeverity {
    INFO = 0,
    SUCCESS = 1,
    WARNING = 2,
    ERROR = 3,
}

type SevInfo = {
    print: string
    color: string
}
    
function GetSevInfo(sev: LogSeverity): SevInfo {
    switch (sev) {
        case LogSeverity.INFO: return {print:"INFO", color:'background: #222; color: #3572a3'}
        case LogSeverity.SUCCESS: return {print:"SUCCESS", color:'background: #222; color: #2a8b3f'}
        case LogSeverity.WARNING: return {print:"WARNING", color:'background: #222; color: #c96c2e'}
        case LogSeverity.ERROR: return {print:"ERROR", color:'background: #222; color: #6d1d09'}
    }
}

export default Logger